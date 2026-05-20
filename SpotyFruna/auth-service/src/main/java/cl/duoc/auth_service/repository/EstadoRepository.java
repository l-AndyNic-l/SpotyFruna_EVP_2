package cl.duoc.auth_service.repository;

import cl.duoc.auth_service.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Estado findByNombre(String nombre);
    Boolean existsByNombre(String nombre);
}
