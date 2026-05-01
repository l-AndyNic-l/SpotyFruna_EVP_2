package cl.duoc.registro_session_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "nombreCompleto", "edad", "email", "celular" })
public class UsuarioDTO {

    private Long id;
    private String nombreCompleto;
    private int edad;
    private String email;
    private int celular;

}
