package cl.duoc.suscripciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id", "fecha_inicio", "fecha_termino", "activado", "plan", "usuario"})
public class SuscripcionDTO {

    private Long id;
    private String fecha_inicio;
    private String fecha_termino;
    private String activado;
    private String plan;
    private String usuario;

}
