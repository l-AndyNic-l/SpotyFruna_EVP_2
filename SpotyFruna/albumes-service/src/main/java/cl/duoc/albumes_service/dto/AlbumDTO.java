package cl.duoc.albumes_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"id", "artista", "nombre", "descripcion", "fecha_lanzamiento", "tipo_album"})
public class AlbumDTO {

    private Long id;
    private String artista;
    private String nombre;
    private String descripcion;
    private String fecha_lanzamiento;
    private String tipo_album;
    private List<AlbumCancionDTO> canciones;

}


