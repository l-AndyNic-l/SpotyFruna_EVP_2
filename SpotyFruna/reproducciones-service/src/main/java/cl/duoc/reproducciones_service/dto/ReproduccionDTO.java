package cl.duoc.reproducciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "fecha_reproduccion", "tiempo_escuchado", "dispositivo", "usuario" })
public class ReproduccionDTO {

    private Long id;
    private String fecha_reproduccion;
    private String tiempo_escuchado;
    private String dispositivo;
    private String usuario;
    private CancionDTO cancion;

}
