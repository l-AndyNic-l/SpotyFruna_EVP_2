package cl.duoc.playlists_service.model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Privacidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_privacidad;

    @Size(min = 1, max = 20)
    @Column(nullable = false, unique = true)
    private String nombre_privacidad;
}
