package cl.duoc.suscripciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "fechaInicio", "fechaTermino", "activado", "plan", "usuario"})
@Schema(
        name = "SuscripcionDTO",
        description = "Representa una suscripción con su información principal y su plan"
)
public class SuscripcionDTO {

    @Schema(
            description = "Identificador único de la suscripción",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Fecha de inicio de la suscripción",
            example = "01/01/2024"
    )
    private String fechaInicio;

    @Schema(
            description = "Fecha de término de la suscripción",
            example = "01/01/2025"
    )
    private String fechaTermino;

    @Schema(
            description = "Plan activado",
            example = "Sí"
    )
    private String activado;

    @Schema(
            description = "Plan de la suscripción",
            example = "Premium"
    )
    private String plan;

    @Schema(
            description = "Correo del usuario asociado a la suscripción",
            example = "sabrina@artist.com"
    )
    private String usuario;

}
