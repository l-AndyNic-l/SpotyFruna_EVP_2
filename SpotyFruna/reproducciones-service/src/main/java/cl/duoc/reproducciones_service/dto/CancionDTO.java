package cl.duoc.reproducciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "autor", "titulo", "duracion", "fechaLanzamiento", "genero", "album"})
@Schema(
        name = "CancionDTO",
        description = "Representa una canción con su información principal, genero y album asociado"
)
public class CancionDTO {

    @Schema(
            description = "Identificador único de la canción",
            example = "10"
    )
    private Long id;

    @Schema(
            description = "Nombre del artista asociado a la canción",
            example = "Daft Punk"
    )
    private String autor;

    @Schema(
            description = "Nombre de la canción",
            example = "Instant Crush"
    )
    private String titulo;

    @Schema(
            description = "Duración de la canción (en segundos)",
            example = "333"
    )
    private String duracion;

    @Schema(
            description = "Fecha de lanzamiento de la canción",
            example = "2013-05-17"
    )
    private String fechaLanzamiento;

    @Schema(
            description = "Género de la cancíon",
            example = "Synth-pop"
    )
    private String genero;

    @Schema(
            description = "Nombre del álbum",
            example = "Random Access Memories"
    )
    private String album;

}
