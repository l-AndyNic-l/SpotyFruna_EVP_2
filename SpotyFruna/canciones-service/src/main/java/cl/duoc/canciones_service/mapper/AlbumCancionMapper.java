package cl.duoc.canciones_service.mapper;

import cl.duoc.canciones_service.dto.AlbumCancionDTO;
import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.dto.CancionDTO;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;

@Component
public class AlbumCancionMapper {

    public AlbumCancionDTO toDTO(Cancion c) {

        if(c == null) {
            return null;
        }

        AlbumCancionDTO dto = new AlbumCancionDTO();

        dto.setId(c.getId());
        dto.setTitulo(c.getTitulo());

        int minutos = (int)(c.getDuracion() / 60);
        int segundos = (int)(c.getDuracion() % 60);

        if (segundos < 10) {
            dto.setDuracion(minutos + ":" + "0" + segundos);

        } else {
            dto.setDuracion(minutos + ":" + segundos);
        }

        return dto;
    }

}
