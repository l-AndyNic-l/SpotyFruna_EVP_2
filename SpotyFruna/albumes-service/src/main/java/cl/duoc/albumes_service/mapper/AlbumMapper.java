package cl.duoc.albumes_service.mapper;

import cl.duoc.albumes_service.dto.AlbumCancionDTO;
import cl.duoc.albumes_service.dto.UsuarioDTO;
import cl.duoc.albumes_service.model.Album;
import cl.duoc.albumes_service.dto.AlbumDTO;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class AlbumMapper {

    public AlbumDTO toDTO(Album a, UsuarioDTO u, List <AlbumCancionDTO> c) {
        if (a == null) {
            return null;
        }

        AlbumDTO dto = new AlbumDTO();

        dto.setId(a.getId());
        dto.setArtista(u.getNickname());
        dto.setNombre(a.getNombre());
        dto.setDescripcion(a.getDescripcion());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dto.setFecha_lanzamiento(a.getFechaLanzamiento().format(dtf));
        dto.setTipo_album(a.getTipoAlbum().getNombre());
        dto.setCanciones(c);

        return dto;
    }

}
