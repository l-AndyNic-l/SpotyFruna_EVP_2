package cl.duoc.albumes_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "titulo", "duracion"})
@Schema(
        name = "AlbumCancionDTO",
        description = "Representa una canción asociada a un álbum"
)
public class AlbumCancionDTO {

    @Schema(
            description = "Identificador único de la canción",
            example = "561"
    )
    private Long id;

    @Schema(
            description = "Nombre/Título de la canción",
            example = "Instant Crush"
    )
    private String titulo;

    @Schema(
            description = "Duración de la canción",
            example = "3:33"
    )
    private String duracion;

}
