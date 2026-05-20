package cl.duoc.suscripciones_service.repository;

import cl.duoc.suscripciones_service.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository <Plan, Long> {
    Boolean existsByNombre(String nombre);
}
