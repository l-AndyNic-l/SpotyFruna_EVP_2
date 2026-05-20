package cl.duoc.albumes_service.service;

import cl.duoc.albumes_service.exception.ConflictException;
import cl.duoc.albumes_service.exception.ResourceNotFoundException;
import cl.duoc.albumes_service.model.TipoAlbum;
import cl.duoc.albumes_service.repository.TipoAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAlbumService {

    @Autowired
    private TipoAlbumRepository tipoAlbumRepository;



    public List<TipoAlbum> findAll() {
        return tipoAlbumRepository.findAll();
    }

    public TipoAlbum findById(Long idTipoAlbum) {
        return tipoAlbumRepository.findById(idTipoAlbum).orElseThrow(() -> new ResourceNotFoundException("Tipo álbum no encontrado"));
    }

    public TipoAlbum save(TipoAlbum tipoAlbum) {
        if (tipoAlbumRepository.existsByNombre(tipoAlbum.getNombre())) {
            throw new ConflictException("Ya existe un tipo álbum con ese nombre");
        }
        return tipoAlbumRepository.save(tipoAlbum);
    }

    public TipoAlbum update(Long idTipoAlbum, TipoAlbum tipoAlbumNuevo) {
        TipoAlbum tipoAlbum = tipoAlbumRepository.findById(idTipoAlbum).orElseThrow(() -> new ResourceNotFoundException("Tipo álbum no encontrado"));
        if (tipoAlbumRepository.existsByNombre(tipoAlbumNuevo.getNombre())) {
            throw new ConflictException("Ya existe un tipo álbum con ese nombre");
        }

        tipoAlbum.setNombre(tipoAlbumNuevo.getNombre());

        return tipoAlbumRepository.save(tipoAlbum);
    }

    public void deleteById(Long idTipoAlbum) {
        if(!tipoAlbumRepository.existsById(idTipoAlbum)) {
            throw new ResourceNotFoundException("Tipo álbum no encontrado");
        }
        tipoAlbumRepository.deleteById(idTipoAlbum);
    }

}
