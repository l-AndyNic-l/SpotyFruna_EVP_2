package cl.duoc.albumes_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
