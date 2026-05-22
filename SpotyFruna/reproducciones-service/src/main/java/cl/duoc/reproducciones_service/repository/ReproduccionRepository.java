package cl.duoc.reproducciones_service.repository;

import cl.duoc.reproducciones_service.model.Reproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReproduccionRepository extends JpaRepository<Reproduccion, Long> {
}
