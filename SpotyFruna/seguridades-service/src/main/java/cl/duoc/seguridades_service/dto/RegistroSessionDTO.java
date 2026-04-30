package cl.duoc.seguridades_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.time.LocalDate;

@Data
@JsonPropertyOrder({ "id", "fechaHora", "usuario" })
public class RegistroSessionDTO {

    private Long id;
    private LocalDate fechaHora;
    private UsuarioDTO usuario;

}
