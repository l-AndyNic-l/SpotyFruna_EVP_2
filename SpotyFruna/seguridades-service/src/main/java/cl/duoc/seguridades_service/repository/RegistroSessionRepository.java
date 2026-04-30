package cl.duoc.seguridades_service.repository;

import cl.duoc.seguridades_service.model.RegistroSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroSessionRepository extends JpaRepository<RegistroSession, Long> {
}
