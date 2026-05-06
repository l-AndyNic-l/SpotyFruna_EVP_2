package cl.duoc.auth_service.mapper;

import cl.duoc.auth_service.clients.UsuariosFeign;
import cl.duoc.auth_service.dto.AuthDTO;
import cl.duoc.auth_service.dto.UsuarioDTO;
import cl.duoc.auth_service.model.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    @Autowired
    private UsuariosFeign usuarios;

    public AuthDTO toDTO(Auth a) {
        if(a == null) {
            return null;
        }

        AuthDTO dtoAuth = new AuthDTO();
        UsuarioDTO dtoUsuario = usuarios.findById(a.getUsuario());

        dtoAuth.setId(a.getId());
        dtoAuth.setFechaHora(a.getFechaHora());
        dtoAuth.setUsuario(dtoUsuario);

        return dtoAuth;
    }

}
