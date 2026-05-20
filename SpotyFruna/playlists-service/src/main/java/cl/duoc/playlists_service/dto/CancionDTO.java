package cl.duoc.playlists_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "autor", "titulo", "duracion", "fecha_lanzamiento", "genero", "album"})
public class CancionDTO {

    private Long id;
    private String autor;
    private String titulo;
    private String duracion;
    private String fecha_lanzamiento;
    private String genero;
    private String album;

}
