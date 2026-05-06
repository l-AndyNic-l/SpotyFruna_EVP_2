package cl.duoc.canciones_service.mapper;

import cl.duoc.canciones_service.dto.GeneroDTO;
import cl.duoc.canciones_service.model.Genero;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    public GeneroDTO toDTO(Genero g) {
        if(g == null) {
            return null;
        }

        GeneroDTO dto = new GeneroDTO();

        dto.setNombre_genero(g.getNombre_genero());

        return dto;
    }
}
