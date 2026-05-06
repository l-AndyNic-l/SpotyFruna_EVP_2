package cl.duoc.albumes_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id_tipo_album", "nombre_tipo_album"})
public class TipoAlbumDTO {
    private Long id_tipo_album;
    private String nombre_tipo_album;
}
