package cl.duoc.usuarios_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String nombre_usuario;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 6, max = 12)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean activo;

    @Size(min = 3, max = 50)
    @Column()
    private String pnombre;

    @Size(min = 3, max = 50)
    @Column()
    private String snombre;

    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String appaterno;

    @Size(min = 3, max = 50)
    @Column()
    private String apmaterno;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usr", nullable = false)
    private TipoUsuario tipoUsuario;

}
