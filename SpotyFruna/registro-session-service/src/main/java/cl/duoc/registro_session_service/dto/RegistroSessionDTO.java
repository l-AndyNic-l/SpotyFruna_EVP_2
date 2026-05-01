package cl.duoc.registro_session_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({ "id", "fechaHora", "usuario" })
public class RegistroSessionDTO {

    private Long id;
    private LocalDateTime fechaHora;
    private UsuarioDTO usuario;

}
