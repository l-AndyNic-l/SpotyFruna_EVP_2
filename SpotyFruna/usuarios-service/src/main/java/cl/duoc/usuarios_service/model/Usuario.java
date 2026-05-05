package cl.duoc.usuarios_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String nombre;

    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String apellido;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 6, max = 12)
    @Column(nullable = false)
    private String password;

    private int celular;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn( name = "id_tipo", nullable = false)
    private TipoUsuario tipoUsuario;

}
