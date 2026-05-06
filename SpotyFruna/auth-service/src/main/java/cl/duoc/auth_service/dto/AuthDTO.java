package cl.duoc.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({ "id", "fechaHora", "usuario" })
public class AuthDTO {

    private Long id;
    private LocalDateTime fechaHora;
    private UsuarioDTO usuario;

}
