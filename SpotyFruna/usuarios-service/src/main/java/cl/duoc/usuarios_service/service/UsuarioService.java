package cl.duoc.usuarios_service.service;

import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.mapper.UsuarioMapper;
import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper mapper;

    public List<UsuarioDTO> findAll() {
        List<UsuarioDTO> listado = new ArrayList<>();

        for(Usuario u : usuarioRepository.findAll()) {
            UsuarioDTO u_dto = mapper.toDTO(u);
            listado.add(u_dto);
        }

        return listado;
    }

    public UsuarioDTO findById(Long id) {
        Usuario u =  usuarioRepository.findById(id).orElse(null);
        return mapper.toDTO(u);
    }

    public Usuario save(Usuario u) {
        //if(u.getTipo().equalsIgnoreCase("Cliente") || u.getTipo().equalsIgnoreCase("Artista")) {
        //    return usuarioRepository.save(u);
        //} else {
        //    return null;
        //}
        return usuarioRepository.save(u);
    }

    public int update(Usuario u, Long id) {
        //if(u.getTipo().equalsIgnoreCase("Cliente") || u.getTipo().equalsIgnoreCase("Artista")) {
        //    return usuarioRepository.update(u, id);
        //} else {
        //    return 0;
        //}
        return usuarioRepository.update(u, id);
    }

    public Boolean deleteById(Long id) {
        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
