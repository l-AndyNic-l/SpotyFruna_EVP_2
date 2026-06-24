package cl.duoc.canciones_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "autor", "titulo", "duracion", "fechaLanzamiento", "genero", "album"})
public class CancionDTO {

    private Long id;
    private String autor;
    private String titulo;
    private String duracion;
    private String fechaLanzamiento;
    private String genero;
    private String album;

}
