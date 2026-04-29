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

        dto.setId(u.getId());
        dto.setNombreCompleto(u.getNombre() + " " + u.getApellido());
        dto.setEdad(calcularEdad(u.getFechaNacimiento()));
        dto.setEmail(u.getEmail());
        dto.setCelular(u.getCelular());

        return dto;
    }

    public int calcularEdad(LocalDate f) {
        Period p = Period.between(f, LocalDate.now());
        int annios = p.getYears() * 12;
        int meses = p.getMonths();
        int edad = ( annios + meses ) / 12;

        return edad;
    }

}
