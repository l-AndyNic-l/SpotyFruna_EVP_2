package cl.duoc.albumes_service.repository;

import cl.duoc.albumes_service.model.Album;
import cl.duoc.albumes_service.model.TipoAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAlbumRepository extends JpaRepository<TipoAlbum, Long> {
    int update(@Param("ta") TipoAlbum a, @Param("id") Long id);
}
