package cl.duoc.playlists_service.dto;

import cl.duoc.playlists_service.dto.GeneroDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id_cancion", "autor", "titulo", "duracion", "fecha_lanzamiento", "id_genero"})
public class CancionDTO {
    private Long id_cancion;
    private String autor;
    private String titulo;
    private Long duracion;
    private LocalDate fecha_lanzamiento;
    private GeneroDTO genero;
}
