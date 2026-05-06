package cl.duoc.usuarios_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Queue;

@Data
@JsonPropertyOrder({ "id", "nombreCompleto", "email"})
public class UsuarioDTO {

    private Long id;
    private String nombreCompleto;
    private String email;

}
