package cl.duoc.albumes_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"id", "artista", "nombre", "descripcion", "fechaLanzamiento", "tipoAlbum"})
public class AlbumDTO {

    private Long id;
    private String artista;
    private String nombre;
    private String descripcion;
    private String fechaLanzamiento;
    private String tipoAlbum;
    private List<AlbumCancionDTO> canciones;

}


