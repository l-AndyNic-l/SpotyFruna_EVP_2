package cl.duoc.playlists_service.model;

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
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_playlist;

    @Size(min = 1, max = 50)
    @Column(nullable = false, unique = true)
    private String nombre_playlist;

    @Size(min = 1, max = 300)
    @Column
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "id_privacidad", nullable = false)
    private Privacidad privacidad;
}
