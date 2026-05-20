package cl.duoc.usuarios_service.service;

import cl.duoc.usuarios_service.exception.BadRequestException;
import cl.duoc.usuarios_service.exception.ConflictException;
import cl.duoc.usuarios_service.exception.ResourceNotFoundException;
import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.repository.TipoUsuarioRepository;
import cl.duoc.usuarios_service.repository.UsuarioRepository;
import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private UsuarioMapper mapper;



    public List<UsuarioDTO> findAll() {
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        for(Usuario usuario : usuarioRepository.findAll()) {
            UsuarioDTO usuarioDTO = mapper.toDTO(usuario);
            usuariosDTO.add(usuarioDTO);
        }

        return usuariosDTO;
    }

    public UsuarioDTO findById(Long idUsuario) {
        if (idUsuario == null || idUsuario < 1) {
            throw new BadRequestException("idUsuario no puede ser menor a 1 ni nulo");
        }
        Usuario usuario =  usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return mapper.toDTO(usuario);
    }

    public List<UsuarioDTO> findAllByTipoUsuario(Long idTipoUsuario) {
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        if (idTipoUsuario == null || idTipoUsuario < 1) {
            throw new BadRequestException("idTipoUsuario no puede ser menor a 1 ni nulo");
        }

        if (!tipoUsuarioRepository.existsById(idTipoUsuario)) {
            throw new ResourceNotFoundException("Tipo de usuario no encontrado");
        }

        for (Usuario usuario : usuarioRepository.findAll()) {
            if (Objects.equals(usuario.getTipoUsuario().getId(), idTipoUsuario)) {
                UsuarioDTO usuarioDTO = mapper.toDTO(usuario);
                usuariosDTO.add(usuarioDTO);
            }
        }

        return usuariosDTO;
    }

    public List<UsuarioDTO> findAllByNickname(String nickname) {
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        if (nickname == null || nickname.isBlank()) {
            throw new BadRequestException("nickname no puede ser nulo ni vacío");
        }

        for (Usuario usuario : usuarioRepository.findAll()) {
            if (Objects.equals(usuario.getNickname(), nickname)) {
                UsuarioDTO usuarioDTO = mapper.toDTO(usuario);
                usuariosDTO.add(usuarioDTO);
            }
        }

        return usuariosDTO;
    }

    public List<UsuarioDTO> findAllBetweenDates(LocalDate fechaMin, LocalDate fechaMax) {
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        if (fechaMin == null || fechaMax == null) {
            throw new BadRequestException("Fechas no pueden ser nulas");
        } else if (fechaMin.isAfter(fechaMax)) {
            throw new BadRequestException("Fecha mínima no puede ser mayor a fecha máxima");
        } else if (fechaMin.getYear() > LocalDate.now().getYear() || fechaMax.getYear() > LocalDate.now().getYear()) {
            throw new BadRequestException("El año de las fechas no puede ser mayor a " + LocalDate.now().getYear());
        }

        for (Usuario usuario : usuarioRepository.findAll()) {
            LocalDate fechaNacimiento = usuario.getFechaNacimiento();

            if (
                (fechaNacimiento.isAfter(fechaMin)
                || fechaNacimiento.isEqual(fechaMin))
                &&
                (fechaNacimiento.isBefore(fechaMax)
                || fechaNacimiento.isEqual(fechaMax))

            ) {
                UsuarioDTO usuarioDTO = mapper.toDTO(usuario);
                usuariosDTO.add(usuarioDTO);
            }
        }

        return usuariosDTO;
    }

    public Usuario save(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new ConflictException("Ya existe un usuario con ese email");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long idUsuario, Usuario usuarioNuevo) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Usuario usuarioEmail = usuarioRepository.findByEmail(usuarioNuevo.getEmail());

        if (usuarioEmail != null && !Objects.equals(usuarioEmail.getId(), idUsuario)) {
            throw new ConflictException("Ya existe un usuario con ese email");
        }

        usuario.setNombre(usuarioNuevo.getNombre());
        usuario.setApellido(usuarioNuevo.getApellido());
        usuario.setNickname(usuarioNuevo.getNickname());
        usuario.setEmail(usuarioNuevo.getEmail());
        usuario.setPassword(usuarioNuevo.getPassword());
        usuario.setFechaNacimiento(usuarioNuevo.getFechaNacimiento());
        usuario.setCelular(usuarioNuevo.getCelular());
        usuario.setTipoUsuario(usuarioNuevo.getTipoUsuario());

        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long idUsuario) {
        if(!usuarioRepository.existsById(idUsuario)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(idUsuario);
    }

}
