package cl.duoc.albumes_service.mapper;

import cl.duoc.albumes_service.dto.TipoAlbumDTO;
import cl.duoc.albumes_service.model.TipoAlbum;
import org.springframework.stereotype.Component;

@Component
public class TipoAlbumMapper {
    public TipoAlbumDTO toDTO(TipoAlbum ta) {
        if(ta == null) {
            return null;
        }

        TipoAlbumDTO dto = new TipoAlbumDTO();

        dto.setNombre_tipo_album(ta.getNombre_tipo_album());

        return dto;
    }
}
