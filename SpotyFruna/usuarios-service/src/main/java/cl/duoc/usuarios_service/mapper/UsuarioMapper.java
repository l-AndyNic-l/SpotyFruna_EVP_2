package cl.duoc.usuarios_service.mapper;

import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.model.Usuario;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Period;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario u) {
        if(u == null) {
            return null;
        }

        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(u.getId_usuario());
        dto.setNombreCompleto(u.getPnombre() + " " + u.getSnombre() + " " + u.getAppaterno() + " " + u.getApmaterno());
        dto.setEmail(u.getEmail());

        return dto;
    }

}
