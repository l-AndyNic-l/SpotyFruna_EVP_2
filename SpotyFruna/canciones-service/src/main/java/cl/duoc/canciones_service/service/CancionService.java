package cl.duoc.canciones_service.service;

import cl.duoc.canciones_service.dto.CancionDTO;
import cl.duoc.canciones_service.mapper.CancionMapper;
import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepository;
    
    @Autowired
    private CancionMapper mapper;

    public List<CancionDTO> findAll() {
        List<CancionDTO> listado = new ArrayList<>();

        for(Cancion c : cancionRepository.findAll()) {
            CancionDTO c_dto = mapper.toDTO(c);
            listado.add(c_dto);
        }

        return listado;
    }

    public CancionDTO findById(Long id) {
        Cancion c =  cancionRepository.findById(id).orElse(null);
        return mapper.toDTO(c);
    }

    public Cancion save(Cancion c) {
        return cancionRepository.save(c);
    }

    public int update(Cancion c, Long id) {
        return cancionRepository.update(c, id);
    }

    public Boolean deleteById(Long id) {
        if(cancionRepository.existsById(id)) {
            cancionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
