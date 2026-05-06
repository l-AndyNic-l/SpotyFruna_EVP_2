package cl.duoc.albumes_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_album;

    @Size(min = 1, max = 50)
    @Column(nullable = false, unique = true)
    private String nombre_album;

    @Size(min = 1, max = 300)
    @Column
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fecha_lanzamiento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_album", nullable = false)
    private TipoAlbum tipo_album;
}
