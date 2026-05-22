package cl.duoc.reportes_service.model;

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
@Table(name = "reporte")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long administrador;

    @Column(nullable = false)
    private Long usuario;

    @Size(min = 10)
    @Column(nullable = false)
    private String descripcion;

    private LocalDate fechaEnviado = LocalDate.now();
    private LocalDate fechaResuelto;

    @ManyToOne
    @JoinColumn(name = "id_tipo_reporte", nullable = false)
    private TipoReporte tipoReporte;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

}
