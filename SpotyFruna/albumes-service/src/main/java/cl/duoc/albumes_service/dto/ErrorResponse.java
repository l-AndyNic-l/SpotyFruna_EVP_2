package cl.duoc.albumes_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Estructura estándar de respuesta cuando ocurre un error en la API"
)
public class ErrorResponse {
    @Schema(description = "Fecha y hora en que ocurrió el error")
    private LocalDateTime fecha;

    @Schema(description = "Código HTTP del error")
    private int codigo;

    @Schema(description = "Tipo de error HTTP")
    private String error;

    @Schema(description = "Mensaje descriptivo del error")
    private String mensaje;
}
