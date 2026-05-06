package cl.duoc.canciones_service.mapper;

import cl.duoc.canciones_service.dto.CancionDTO;
import cl.duoc.canciones_service.model.Cancion;
import org.springframework.stereotype.Component;

@Component
public class CancionMapper {

    public CancionDTO toDTO(Cancion c) {
        if(c == null) {
            return null;
        }

        CancionDTO dto = new CancionDTO();

        dto.setAutor(c.getAutor());
        dto.setTitulo(c.getTitulo());
        dto.setDuracion(c.getDuracion());
        dto.setFecha_lanzamiento(c.getFecha_lanzamiento());
        dto.setGenero(c.getGenero());

        return dto;
    }
}
