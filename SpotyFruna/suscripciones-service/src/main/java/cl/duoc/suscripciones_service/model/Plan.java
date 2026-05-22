package cl.duoc.suscripciones_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 30)
    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private int precio;

    @Column(nullable = false)
    private Boolean anuncios;

    @Column(nullable = false)
    private Double tamanioDescargas;

}
