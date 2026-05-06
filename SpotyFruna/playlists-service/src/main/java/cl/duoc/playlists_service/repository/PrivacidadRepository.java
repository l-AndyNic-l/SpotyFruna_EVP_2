package cl.duoc.playlists_service.repository;

import cl.duoc.playlists_service.model.Privacidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacidadRepository extends JpaRepository<Privacidad, Long> {
    Privacidad findByPrivacidad(String privacidad);
}
