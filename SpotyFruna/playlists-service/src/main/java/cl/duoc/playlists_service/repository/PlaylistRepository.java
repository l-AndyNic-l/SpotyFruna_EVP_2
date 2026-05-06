package cl.duoc.playlists_service.repository;

import cl.duoc.playlists_service.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    int update(@Param("p") Playlist p, @Param("id") Long id);
}
