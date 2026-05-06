package cl.duoc.albumes_service.dto;

import cl.duoc.albumes_service.model.TipoAlbum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonPropertyOrder({ "id_album", "nombre_album", "descripcion, fecha_lanzamiento, tipo_album, canciones "})
public class AlbumDTO {

    private Long id_album;
    private String nombre_album;
    private String descripcion;
    private LocalDate fecha_lanzamiento;
    private TipoAlbum tipo_album;
    private List<CancionDTO> canciones;
    private List<GeneroDTO> generos;
}
