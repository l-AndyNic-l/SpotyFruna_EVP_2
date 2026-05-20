package cl.duoc.canciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "autor", "titulo", "duracion", "genero"})
public class AlbumCancionDTO {

    private Long id;
    private String titulo;
    private String duracion;

}