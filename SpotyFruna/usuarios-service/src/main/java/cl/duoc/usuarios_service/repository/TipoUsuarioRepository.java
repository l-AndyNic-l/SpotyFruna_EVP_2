package cl.duoc.usuarios_service.repository;

import cl.duoc.usuarios_service.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario,Long> {

    TipoUsuario findByTipo(String tipo);

}
