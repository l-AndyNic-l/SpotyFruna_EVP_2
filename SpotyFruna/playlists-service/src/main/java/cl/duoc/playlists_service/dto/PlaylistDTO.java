package cl.duoc.playlists_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@JsonPropertyOrder({"id", "nombre", "descripcion", "fechaCreacion", "privacidad", "usuario", "canciones"})
@Schema(
        name = "PlaylistDTO",
        description = "Representa una canción con su información principal, genero y album asociado"
)
public class PlaylistDTO {

    @Schema(
            description = "Identificador único de la playlist",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Nombre de la playlist",
            example = "Rock Clásico"
    )
    private String nombre;

    @Schema(
            description = "Descripción de la playlist",
            example = "Los mejores temas de rock de los 70s y 80s"
    )
    private String descripcion;

    @Schema(
            description = "Fecha de creacíón de la playlist",
            example = "15/01/2024"
    )
    private String fechaCreacion;

    @Schema(
            description = "Tipo de privacidad de la playlist",
            example = "Pública"
    )
    private String privacidad;

    @Schema(
            description = "Correo del usuario",
            example = "sabrina@artist.com"
    )
    private String usuario;

    @Schema(
            description = "Canciones asociadas a la playlist",
            example = """
                      [
                         {
                            "id": 1,
                            "autor": "Queen",
                            "titulo": "Bohemian Rhapsody",
                            "duracion": "5:54",
                            "fechaLanzamiento": "31/10/1975",
                            "genero": "Rock",
                            "album": "Bohemian Rhapsody"
                         }
                      ]
                      """
    )
    private List<CancionDTO> canciones;

}
