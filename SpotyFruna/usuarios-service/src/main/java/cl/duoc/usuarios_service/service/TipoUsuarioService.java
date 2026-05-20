package cl.duoc.usuarios_service.service;

import cl.duoc.usuarios_service.exception.BadRequestException;
import cl.duoc.usuarios_service.exception.ConflictException;
import cl.duoc.usuarios_service.exception.ResourceNotFoundException;
import cl.duoc.usuarios_service.model.TipoUsuario;
import cl.duoc.usuarios_service.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;



    public List<TipoUsuario> findAll() {
        return tipoUsuarioRepository.findAll();
    }

    public TipoUsuario findByNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new BadRequestException("Nombre no puede ser nulo ni vacío");
        }

        TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNombre(nombre);

        if (tipoUsuario == null) {
            throw new ResourceNotFoundException("Tipo de usuario no encontrado");
        }

        return tipoUsuario;
    }

    public TipoUsuario save(TipoUsuario tipoUsuario) {
        if (tipoUsuarioRepository.existsByNombre(tipoUsuario.getNombre())) {
            throw new ConflictException("Ya existe un tipo de usuario con ese nombre");
        }
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    public TipoUsuario update(Long idTipoUsuario, TipoUsuario tipoUsuarioNuevo) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(idTipoUsuario).orElseThrow(() -> new ResourceNotFoundException("Tipo de usuario no encontrado"));

        if (tipoUsuarioRepository.existsByNombre(tipoUsuarioNuevo.getNombre())) {
            throw new ConflictException("Ya existe un tipo de usuario con ese nombre");
        }
        tipoUsuario.setNombre(tipoUsuarioNuevo.getNombre());

        return tipoUsuarioRepository.save(tipoUsuario);
    }

    public void deleteById(Long idTipoUsuario) {
        if (!tipoUsuarioRepository.existsById(idTipoUsuario)) {
            throw new ResourceNotFoundException("Tipo de usuario no encontrado");
        }
        tipoUsuarioRepository.deleteById(idTipoUsuario);
    }

}
