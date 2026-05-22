package cl.duoc.canciones_service.repository;

import cl.duoc.canciones_service.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {

    List<Cancion> findAllByIdAlbum(Long idAlbum);
    Boolean existsByAutor(String autor);

}
