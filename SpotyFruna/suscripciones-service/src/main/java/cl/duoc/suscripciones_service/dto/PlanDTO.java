package cl.duoc.suscripciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "nombre", "precio", "anuncios"})
public class PlanDTO {

    private Long id;
    private String nombre;
    private String precio;
    private String anuncios;
    private String tamanio_descargas;

}
