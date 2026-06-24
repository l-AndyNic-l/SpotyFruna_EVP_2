package cl.duoc.suscripciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "fechaInicio", "fechaTermino", "activado", "plan", "usuario"})
public class SuscripcionDTO {

    private Long id;
    private String fechaInicio;
    private String fechaTermino;
    private String activado;
    private String plan;
    private String usuario;

}
