package cl.duoc.usuarios_service.service;

import cl.duoc.usuarios_service.model.TipoUsuario;
import cl.duoc.usuarios_service.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public List<TipoUsuario> findAll(){
        return tipoUsuarioRepository.findAll();
    }

    public TipoUsuario findByTipo(String tipo) {
        return tipoUsuarioRepository.findByTipo(tipo);
    }

    public TipoUsuario save(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    public Boolean deleteById(Long id) {
        if(tipoUsuarioRepository.existsById(id)){
            tipoUsuarioRepository.deleteById(id);
            return true;

        } else return false;
    }

}
