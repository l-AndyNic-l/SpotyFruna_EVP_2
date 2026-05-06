package cl.duoc.playlists_service.dto;
import cl.duoc.playlists_service.model.Privacidad;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonPropertyOrder({"id_playlist", "nombre_playlist", "descripcion", "fecha_creacion", "id_privacidad"})
public class PlaylistDTO {

    private Long id_playlist;
    private String nombre_playlist;
    private String descripcion;
    private LocalDate fecha_creacion;
    private Privacidad privacidad;
    private List<CancionDTO> canciones;
}
