package cl.duoc.canciones_service.repository;

import cl.duoc.canciones_service.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    Boolean existsByNombre(String nombre);
}
