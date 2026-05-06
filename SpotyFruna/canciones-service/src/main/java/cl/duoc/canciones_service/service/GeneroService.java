package cl.duoc.canciones_service.service;

import cl.duoc.canciones_service.dto.GeneroDTO;
import cl.duoc.canciones_service.mapper.GeneroMapper;
import cl.duoc.canciones_service.model.Genero;
import cl.duoc.canciones_service.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private GeneroMapper mapper;

    public List<GeneroDTO> findAll() {
        List<GeneroDTO> listado = new ArrayList<>();

        for(Genero g : generoRepository.findAll()) {
            GeneroDTO g_dto = mapper.toDTO(g);
            listado.add(g_dto);
        }

        return listado;
    }

    public GeneroDTO findById(Long id) {
        Genero c =  generoRepository.findById(id).orElse(null);
        return mapper.toDTO(c);
    }

    public Genero save(Genero g) {
        return generoRepository.save(g);
    }

    public int update(Genero g, Long id) {
        return generoRepository.update(g, id);
    }

    public Boolean deleteById(Long id) {
        if(generoRepository.existsById(id)) {
            generoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
