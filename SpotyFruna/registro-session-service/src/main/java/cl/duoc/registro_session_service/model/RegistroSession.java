package cl.duoc.registro_session_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegistroSession {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false )
    private Long usuario;

    @Column( nullable = false )
    private LocalDateTime fechaHora;

    @Column( nullable = false )
    private String token;

}
