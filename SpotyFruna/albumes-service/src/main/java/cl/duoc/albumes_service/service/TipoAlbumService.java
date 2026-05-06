package cl.duoc.albumes_service.service;

import cl.duoc.albumes_service.dto.AlbumDTO;
import cl.duoc.albumes_service.dto.TipoAlbumDTO;
import cl.duoc.albumes_service.mapper.AlbumMapper;
import cl.duoc.albumes_service.mapper.TipoAlbumMapper;
import cl.duoc.albumes_service.model.Album;
import cl.duoc.albumes_service.model.TipoAlbum;
import cl.duoc.albumes_service.repository.AlbumRepository;
import cl.duoc.albumes_service.repository.TipoAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoAlbumService {

    @Autowired
    private TipoAlbumRepository tipoAlbumRepository;

    @Autowired
    private TipoAlbumMapper mapper;

    public List<TipoAlbumDTO> findAll() {
        List<TipoAlbumDTO> listado = new ArrayList<>();

        for(TipoAlbum ta : tipoAlbumRepository.findAll()) {
            TipoAlbumDTO ta_dto = mapper.toDTO(ta);
            listado.add(ta_dto);
        }

        return listado;
    }

    public TipoAlbumDTO findById(Long id) {
        TipoAlbum ta =  tipoAlbumRepository.findById(id).orElse(null);
        return mapper.toDTO(ta);
    }

    public TipoAlbum save(TipoAlbum ta) {
        return tipoAlbumRepository.save(ta);
    }

    public int update(TipoAlbum ta, Long id) {
        return tipoAlbumRepository.update(ta, id);
    }

    public Boolean deleteById(Long id) {
        if(tipoAlbumRepository.existsById(id)) {
            tipoAlbumRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
