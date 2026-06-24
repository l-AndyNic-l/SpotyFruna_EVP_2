package cl.duoc.reproducciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "fechaReproduccion", "tiempoEscuchado", "dispositivo", "usuario" })
@Schema(
        name = "ReproduccionDTO",
        description = "Representa una reproduccion de una canción por un usuario con su información principal y el tipo de dispositivo asociado"
)
public class ReproduccionDTO {

    @Schema(
            description = "Identificador único de la reproducción",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Fecha de la reproducción",
            example = "2026-03-05"
    )
    private String fechaReproduccion;

    @Schema(
            description = "Duración de la reproducción (en segundos)",
            example = "1"
    )
    private String tiempoEscuchado;

    @Schema(
            description = "Tipo de dispositivo utilizado",
            example = "Móvil"
    )
    private String dispositivo;

    @Schema(
            description = "Correo del usuario que reprodujo la canción",
            example = "be.albornozc@duocuc.cl"
    )
    private String usuario;

    @Schema(
            description = "Canción reproducida",
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
