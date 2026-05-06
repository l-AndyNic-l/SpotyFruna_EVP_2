package cl.duoc.albumes_service.mapper;

import cl.duoc.albumes_service.clients.CancionesFeign;
import cl.duoc.albumes_service.clients.GenerosFeign;
import cl.duoc.albumes_service.dto.AlbumDTO;
import cl.duoc.albumes_service.dto.CancionDTO;
import cl.duoc.albumes_service.dto.GeneroDTO;
import cl.duoc.albumes_service.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumMapper {

    @Autowired
    private CancionesFeign canciones;
    private GenerosFeign generos;

    public AlbumDTO toDTO(Album a) {
        if(a == null) {
            return null;
        }

        AlbumDTO dto = new AlbumDTO();
        List<CancionDTO> dtoCanciones = canciones.findAll();

        dto.setNombre_album(a.getNombre_album());
        dto.setDescripcion(a.getDescripcion());
        dto.setFecha_lanzamiento(a.getFecha_lanzamiento());
        dto.setTipo_album(a.getTipo_album());
        dto.setCanciones(dtoCanciones);

        return dto;
    }
}
