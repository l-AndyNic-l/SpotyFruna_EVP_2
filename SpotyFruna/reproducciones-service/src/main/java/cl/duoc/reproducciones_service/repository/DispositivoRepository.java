package cl.duoc.reproducciones_service.repository;

import cl.duoc.reproducciones_service.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    Dispositivo findByNombre( String nombre );
}
