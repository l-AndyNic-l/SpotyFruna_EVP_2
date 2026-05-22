package cl.duoc.suscripciones_service.mapper;

import cl.duoc.suscripciones_service.dto.PlanDTO;
import cl.duoc.suscripciones_service.model.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    public PlanDTO toDTO(Plan p) {

        if(p == null) {
            return null;
        }

        PlanDTO dto = new PlanDTO();

        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setPrecio("$" + p.getPrecio());

        if(p.getAnuncios()) {
            dto.setAnuncios("Con Anuncios");

        } else  {
            dto.setAnuncios("Sin Anuncios");
        }

        dto.setTamanio_descargas(p.getTamanioDescargas() + " MB");

        return dto;

    }

}
