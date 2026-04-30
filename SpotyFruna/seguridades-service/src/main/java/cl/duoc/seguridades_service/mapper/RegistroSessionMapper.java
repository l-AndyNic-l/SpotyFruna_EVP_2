package cl.duoc.seguridades_service.mapper;

import cl.duoc.seguridades_service.model.RegistroSession;
import org.springframework.stereotype.Component;

@Component
public class RegistroSessionMapper {

    public RegistroSession toDTO(RegistroSession r) {
        if(r == null) {
            return null;
        }

        RegistroSession dto = new RegistroSession();

        

        return dto;
    }

}
