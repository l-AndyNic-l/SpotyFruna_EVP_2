package cl.duoc.suscripciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "nombre", "precio", "anuncios"})
@Schema(
        name = "PlanDTO",
        description = "Representa un plan con su información principal"
)
public class PlanDTO {

    @Schema(
            description = "Identificador único del plan",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Nombre del plan",
            example = "Gratuito"
    )
    private String nombre;

    @Schema(
            description = "Precio del plan",
            example = "$0"
    )
    private String precio;

    @Schema(
            description = "Anuncios activados/desactivados del plan",
            example = "Con anuncios"
    )
    private String anuncios;

    @Schema(
            description = "Nombre del plan",
            example = "0.0 MB"
    )
    private String tamanioDescargas;

}
