package cl.duoc.registro_session_service.mapper;

import cl.duoc.registro_session_service.clients.UsuariosFeign;
import cl.duoc.registro_session_service.dto.RegistroSessionDTO;
import cl.duoc.registro_session_service.dto.UsuarioDTO;
import cl.duoc.registro_session_service.model.RegistroSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistroSessionMapper {

    @Autowired
    private UsuariosFeign usuarios;

    public RegistroSessionDTO toDTO(RegistroSession r) {
        if(r == null) {
            return null;
        }

        RegistroSessionDTO dtoRegistro = new RegistroSessionDTO();
        UsuarioDTO dtoUsuario = usuarios.findById(r.getUsuario());

        dtoRegistro.setId(r.getId());
        dtoRegistro.setFechaHora(r.getFechaHora());
        dtoRegistro.setUsuario(dtoUsuario);

        return dtoRegistro;
    }

}
