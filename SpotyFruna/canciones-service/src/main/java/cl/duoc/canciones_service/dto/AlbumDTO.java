package cl.duoc.canciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"idAlbum", "artista", "nombre", "descripcion", "fechaLanzamiento"})
public class AlbumDTO {

    private Long idAlbum;
    private String artista;
    private String nombre;
    private String descripcion;
    private String fechaLanzamiento;
    private String tipoAlbum;

}


