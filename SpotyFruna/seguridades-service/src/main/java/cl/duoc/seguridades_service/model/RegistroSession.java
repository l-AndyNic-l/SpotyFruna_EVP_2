package cl.duoc.seguridades_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate fechaHora;

    @Column( nullable = false )
    private String token;

}
