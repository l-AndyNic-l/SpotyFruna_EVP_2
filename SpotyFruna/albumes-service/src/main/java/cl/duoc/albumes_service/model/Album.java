package cl.duoc.albumes_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String nombre;

    @Size(min = 1, max = 300)
    @Column
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaLanzamiento = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "id_tipo_album", nullable = false)
    private TipoAlbum tipoAlbum;

    @Column(nullable = false)
    private Long artista;

}

