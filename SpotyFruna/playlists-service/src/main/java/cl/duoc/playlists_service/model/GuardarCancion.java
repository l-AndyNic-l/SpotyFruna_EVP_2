package cl.duoc.playlists_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "guardar_cancion")
public class GuardarCancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_playlist", nullable = false)
    private Playlist playlist;

    @Column(nullable = false)
    private Long idCancion;

}
