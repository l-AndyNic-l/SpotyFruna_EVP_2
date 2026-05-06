package cl.duoc.albumes_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TipoAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_album;

    @Size(min = 1, max = 30)
    @Column(nullable = false, unique = true)
    private String nombre_tipo_album;
}
