package cl.duoc.usuarios_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String nombre;

    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String apellido;

    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true)
    private String nickname;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 6, max = 12)
    @Column(nullable = false)
    private String password;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private int celular;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_USR", nullable = false)
    private TipoUsuario tipoUsuario;

}
