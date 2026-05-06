package cl.duoc.canciones_service.repository;

import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    int update(@Param("g") Genero g, @Param("id") Long id);
}
