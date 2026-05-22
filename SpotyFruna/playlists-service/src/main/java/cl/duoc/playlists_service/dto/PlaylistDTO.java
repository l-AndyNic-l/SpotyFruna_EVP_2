package cl.duoc.playlists_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@JsonPropertyOrder({"id", "nombre", "descripcion", "fecha_creacion", "privacidad", "usuario", "canciones"})
public class PlaylistDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String fecha_creacion;
    private String privacidad;
    private String usuario;
    private List<CancionDTO> canciones;

}
