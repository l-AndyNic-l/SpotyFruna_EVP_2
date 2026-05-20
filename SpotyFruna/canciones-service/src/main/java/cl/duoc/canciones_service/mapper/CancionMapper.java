package cl.duoc.canciones_service.mapper;

import cl.duoc.canciones_service.dto.AlbumDTO;
import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.dto.CancionDTO;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;

@Component
public class CancionMapper {

    public CancionDTO toDTO(Cancion c, AlbumDTO a) {

        if(c == null) {
            return null;
        }

        CancionDTO dto = new CancionDTO();

        dto.setId(c.getId());
        dto.setAutor(c.getAutor());
        dto.setTitulo(c.getTitulo());

        int minutos = (int)(c.getDuracion() / 60);
        int segundos = (int)(c.getDuracion() % 60);

        if (segundos < 10) {
            dto.setDuracion(minutos + ":" + "0" + segundos);

        } else {
            dto.setDuracion(minutos + ":" + segundos);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dto.setFecha_lanzamiento(c.getFechaLanzamiento().format(dtf));
        dto.setGenero(c.getGenero().getNombre());
        dto.setAlbum(a.getNombre());

        return dto;
    }

}
