package cl.duoc.albumes_service.repository;

import cl.duoc.albumes_service.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    int update(@Param("a") Album a, @Param("id") Long id);
}
