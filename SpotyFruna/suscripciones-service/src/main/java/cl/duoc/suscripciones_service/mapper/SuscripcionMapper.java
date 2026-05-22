package cl.duoc.suscripciones_service.mapper;

import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.dto.UsuarioDTO;
import cl.duoc.suscripciones_service.model.Suscripcion;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class SuscripcionMapper {

    public SuscripcionDTO toDTO(Suscripcion s, UsuarioDTO u) {

        if(s == null) {
            return null;
        }

        SuscripcionDTO dto = new SuscripcionDTO();

        dto.setId(s.getId());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dto.setFecha_inicio(s.getFechaInicio().format(dtf));
        dto.setFecha_termino(s.getFechaTermino().format(dtf));

        if( s.getActivado() ) {
            dto.setActivado("Si");

        } else {
            dto.setActivado("No");
        }

        dto.setPlan(s.getPlan().getNombre());
        dto.setUsuario(u.getEmail());

        return dto;
    }

}
