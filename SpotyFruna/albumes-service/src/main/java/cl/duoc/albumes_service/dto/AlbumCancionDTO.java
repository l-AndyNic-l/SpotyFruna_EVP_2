package cl.duoc.albumes_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "titulo", "duracion"})
public class AlbumCancionDTO {

    private Long id;
    private String titulo;
    private String duracion;

}
