package cl.duoc.playlists_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "usuario", "playlist"})
public class GuardarCancionDTO {

    private Long id;
    private String usuario;
    private String playlist;
    private CancionDTO cancion;

}
