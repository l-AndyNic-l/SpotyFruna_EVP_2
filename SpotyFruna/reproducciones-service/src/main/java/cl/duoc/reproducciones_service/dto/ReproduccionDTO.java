package cl.duoc.reproducciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "fechaReproduccion", "tiempoEscuchado", "dispositivo", "usuario" })
public class ReproduccionDTO {

    private Long id;
    private String fechaReproduccion;
    private String tiempoEscuchado;
    private String dispositivo;
    private String usuario;
    private CancionDTO cancion;

}
