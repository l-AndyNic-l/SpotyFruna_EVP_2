package cl.duoc.auth_service.mapper;

import cl.duoc.auth_service.clients.UsuarioClient;
import cl.duoc.auth_service.dto.AuthDTO;
import cl.duoc.auth_service.dto.UsuarioDTO;
import cl.duoc.auth_service.model.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Component
public class AuthMapper {

    @Autowired
    private UsuarioClient usuarios;

    public AuthDTO toDTO(Auth a) {
        if(a == null) {
            return null;
        }

        AuthDTO dtoAuth = new AuthDTO();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        UsuarioDTO dtoUsuario = usuarios.findById(a.getUsuario());

        dtoAuth.setId(a.getId());
        dtoAuth.setFechaRegistro(a.getFechaRegistro().format(dtf));

        if (a.getFechaExpiracion() != null) {
            dtoAuth.setFechaExpiracion(a.getFechaRegistro().format(dtf));

        } else {
            dtoAuth.setFechaExpiracion( "Sin Fecha de Expiracion" );
        }

        if (a.getEstado() != null) {
            dtoAuth.setEstado( a.getEstado().getNombre() );

        } else {
            dtoAuth.setEstado( "Sin Estado" );
        }

        dtoAuth.setToken(a.getToken());
        dtoAuth.setUsuario(dtoUsuario.getEmail());

        return dtoAuth;
    }

}
