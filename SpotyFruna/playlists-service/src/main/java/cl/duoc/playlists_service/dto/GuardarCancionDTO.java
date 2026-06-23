package cl.duoc.playlists_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "usuario", "playlist"})
@Schema(
        name = "GuardarCancionDTO",
        description = "Representa una canción asociada a una playlist y su respectivo usuario responsable"
)
public class GuardarCancionDTO {

    @Schema(
            description = "Identificador único del guardado de canción",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Correo del usuario responsable",
            example = "sabrina@artist.com"
    )
    private String usuario;

    @Schema(
            description = "Nombre de la playlist",
            example = "Rock Clásico"
    )
    private String playlist;

    @Schema(
            description = "Canción asociada a la playlist",
            example = """
                    {
                       "id": 1,
                       "autor": "Queen",
                       "titulo": "Bohemian Rhapsody",
                       "duracion": "5:54",
                       "fechaLanzamiento": "31/10/1975",
                       "genero": "Rock",
                       "album": "Bohemian Rhapsody"
                    }
                      """
    )
    private CancionDTO cancion;

}
