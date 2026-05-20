package cl.duoc.reportes_service.repository;

import cl.duoc.reportes_service.model.TipoReporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoReporteRepository extends JpaRepository<TipoReporte, Long> {
    TipoReporte findByNombre(String nombre);
    Boolean existsByNombre(String nombre);
}
