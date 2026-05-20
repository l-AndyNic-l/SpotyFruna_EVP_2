package cl.duoc.suscripciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "nombreCompleto", "nickname", "email", "edad", "celular"})
public class UsuarioDTO {

    private Long id;
    private String nombreCompleto;
    private String nickname;
    private String email;
    private int edad;
    private int celular;
    private String tipoUsuario;

}
