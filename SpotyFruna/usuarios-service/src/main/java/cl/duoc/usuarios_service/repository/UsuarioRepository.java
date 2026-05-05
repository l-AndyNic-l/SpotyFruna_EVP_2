package cl.duoc.usuarios_service.repository;

import cl.duoc.usuarios_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "UPDATE USUARIO SET nombre = :#{#u.nombre}, apellido = :#{#u.apellido}, email = :#{#u.email}, password = :#{#u.password}, tipo_usuario = :#{#u.tipo_usuario}, celular = :#{#u.celular}, fecha_nacimiento = :#{#u.fechaNacimiento} WHERE id = :id", nativeQuery = true)
    int update(@Param("u") Usuario u, @Param("id") Long id);

}
