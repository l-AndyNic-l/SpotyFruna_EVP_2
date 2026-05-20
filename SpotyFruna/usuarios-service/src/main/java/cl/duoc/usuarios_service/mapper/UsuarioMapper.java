package cl.duoc.usuarios_service.mapper;

import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.dto.UsuarioDTO;
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
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(u.getFechaNacimiento(), ahora);
        int edad = periodo.getYears();

        dto.setId(u.getId());
        dto.setNombreCompleto(u.getNombre() + " " + u.getApellido());
        dto.setNickname(u.getNickname());
        dto.setEmail(u.getEmail());
        dto.setEdad(edad);
        dto.setCelular(u.getCelular());

        if (u.getTipoUsuario() != null) {
            dto.setTipoUsuario(u.getTipoUsuario().getNombre());

        } else {
            dto.setTipoUsuario("No Asignado");
        }

        return dto;
    }

}
