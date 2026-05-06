package cl.duoc.canciones_service.repository;

import cl.duoc.canciones_service.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
    int update(@Param("c") Cancion c, @Param("id") Long id);
}
