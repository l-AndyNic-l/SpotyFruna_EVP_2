package cl.duoc.playlists_service.model;

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
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String nombre;

    @Size(min = 1, max = 300)
    @Column
    private String descripcion;

    private LocalDate fechaCreacion = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "id_privacidad", nullable = false)
    private Privacidad privacidad;

    @Column(nullable = false)
    private Long idUsuario;

}
