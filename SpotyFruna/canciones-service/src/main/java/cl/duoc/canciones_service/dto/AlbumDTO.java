package cl.duoc.canciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"idAlbum", "artista", "nombre", "descripcion", "fechaLanzamiento"})
@Schema(
        name = "AlbumDTO",
        description = "Representa un álbum musical con su información principal, artista y canciones asociadas"
)
public class AlbumDTO {

    @Schema(
            description = "Identificador único del álbum",
            example = "10"
    )
    private Long id;

    @Schema(
            description = "Nombre del artista asociado al álbum",
            example = "Daft Punk"
    )
    private String artista;

    @Schema(
            description = "Nombre del álbum",
            example = "Random Access Memories"
    )
    private String nombre;

    @Schema(
            description = "Descripción del álbum",
            example = "Álbum de música electrónica lanzado en 2013"
    )
    private String descripcion;

    @Schema(
            description = "Fecha de lanzamiento del álbum",
            example = "2013-05-17"
    )
    private String fechaLanzamiento;

    @Schema(
            description = "Tipo o categoría del álbum",
            example = "Album"
    )
    private String tipoAlbum;

}


