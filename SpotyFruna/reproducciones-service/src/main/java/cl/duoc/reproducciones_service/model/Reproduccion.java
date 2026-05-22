package cl.duoc.reproducciones_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reproduccion")
public class Reproduccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaReproduccion = LocalDate.now();

    @Min( value = 1 )
    @Column( nullable = false )
    private int segundosEscuchados;

    @ManyToOne
    @JoinColumn( name = "id_dispositivo", nullable = false )
    private Dispositivo dispositivo;

    @Column(nullable = false)
    private Long cancion;

    @Column(nullable = false)
    private Long usuario;

}
