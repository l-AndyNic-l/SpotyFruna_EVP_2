package cl.duoc.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


@Data
@JsonPropertyOrder({ "id", "fechaRegistro", "fechaExpiracion", "estado", "token" })
public class AuthDTO {

    private Long id;
    private String fechaRegistro;
    private String fechaExpiracion;
    private String estado;
    private String token;
    private String usuario;

}
