package cl.duoc.playlists_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "nombreCompleto", "nickname", "email", "edad", "celular"})
@Schema(
        name = "UsuarioDTO",
        description = "Representa un usuario con su información principal y su tipo de usuario"
)
public class UsuarioDTO {

    @Schema(
            description = "Identificador único del usuario",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Nombre completo del usuario",
            example = "Benjamín Gabriel Albornoz Caces"
    )
    private String nombreCompleto;

    @Schema(
            description = "Alias/Apodo del usuario",
            example = "sm0ksito_cl"
    )
    private String nickname;

    @Schema(
            description = "Correo del usuario",
            example = "be.albornozc@duocuc.cl"
    )
    private String email;

    @Schema(
            description = "Edad del usuario",
            example = "19"
    )
    private int edad;

    @Schema(
            description = "Número de telefono del usuario",
            example = "935541643"
    )
    private int celular;

    @Schema(
            description = "Tipo de usuario",
            example = "Cliente"
    )
    private String tipoUsuario;

}
