package cl.duoc.canciones_service.model;

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
@Table(name = "cancion")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 50)
    @Column(nullable = false, unique = true)
    private String autor;

    @Size(min = 1, max = 50)
    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false)
    private Long duracion;

    @Column(nullable = false)
    private LocalDate fechaLanzamiento;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;

    @Column(nullable = false)
    private Long idAlbum;

}
