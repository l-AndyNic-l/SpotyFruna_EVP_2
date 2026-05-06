package cl.duoc.albumes_service.service;

import cl.duoc.albumes_service.dto.AlbumDTO;
import cl.duoc.albumes_service.mapper.AlbumMapper;
import cl.duoc.albumes_service.model.Album;
import cl.duoc.albumes_service.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumMapper mapper;

    public List<AlbumDTO> findAll() {
        List<AlbumDTO> listado = new ArrayList<>();

        for(Album a : albumRepository.findAll()) {
            AlbumDTO a_dto = mapper.toDTO(a);
            listado.add(a_dto);
        }

        return listado;
    }

    public AlbumDTO findById(Long id) {
        Album a =  albumRepository.findById(id).orElse(null);
        return mapper.toDTO(a);
    }

    public Album save(Album a) {
        return albumRepository.save(a);
    }

    public int update(Album a, Long id) {
        return albumRepository.update(a, id);
    }

    public Boolean deleteById(Long id) {
        if(albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
