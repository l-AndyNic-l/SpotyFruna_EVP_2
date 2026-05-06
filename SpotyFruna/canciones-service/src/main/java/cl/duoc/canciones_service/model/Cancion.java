package cl.duoc.canciones_service.model;

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
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cancion;

    @Size(min = 1, max = 50)
    @Column(nullable = false, unique = true)
    private String autor;

    @Size(min = 1, max = 50)
    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false)
    private Long duracion;

    @Column(nullable = false)
    private LocalDate fecha_lanzamiento;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;
}
