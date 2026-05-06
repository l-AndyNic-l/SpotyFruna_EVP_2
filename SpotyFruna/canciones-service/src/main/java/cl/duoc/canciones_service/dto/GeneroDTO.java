package cl.duoc.canciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id_genero", "nombre_genero"})
public class GeneroDTO {
    private Long id_genero;
    private String nombre_genero;
}
