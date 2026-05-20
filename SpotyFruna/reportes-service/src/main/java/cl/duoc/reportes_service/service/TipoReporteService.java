package cl.duoc.reportes_service.service;

import cl.duoc.reportes_service.exception.BadRequestException;
import cl.duoc.reportes_service.exception.ConflictException;
import cl.duoc.reportes_service.exception.ResourceNotFoundException;
import cl.duoc.reportes_service.model.TipoReporte;
import cl.duoc.reportes_service.repository.TipoReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoReporteService {

    @Autowired
    private TipoReporteRepository tipoReporteRepository;



    public List<TipoReporte> findAll() {
        return tipoReporteRepository.findAll();
    }

    public TipoReporte findByNombre(String nombre) {
        if (nombre == null) {
            throw new BadRequestException("Nombre no puede ser nulo");
        }

        TipoReporte tipoReporte = tipoReporteRepository.findByNombre(nombre);

        if (tipoReporte == null) {
            throw new ResourceNotFoundException("Tipo de reporte no encontrado");
        }

        return tipoReporte;
    }

    public TipoReporte save(TipoReporte tipoReporte) {
        if (tipoReporteRepository.existsByNombre(tipoReporte.getNombre())) {
            throw new ConflictException("Ya existe un tipo de reporte con ese nombre");
        }
        return tipoReporteRepository.save(tipoReporte);
    }

    public TipoReporte update(Long idTipoReporte, TipoReporte tipoReporteNuevo) {
        TipoReporte tipoReporte = tipoReporteRepository.findById(idTipoReporte).orElseThrow(() -> new ResourceNotFoundException("Tipo de reporte no encontrado"));

        if (tipoReporteRepository.existsByNombre(tipoReporteNuevo.getNombre())) {
            throw new ConflictException("Ya existe un tipo de reporte con ese nombre");
        }

        tipoReporte.setNombre(tipoReporteNuevo.getNombre());

        return tipoReporteRepository.save(tipoReporte);
    }

    public void deleteById(Long idTipoReporte) {
        if (!tipoReporteRepository.existsById(idTipoReporte)) {
            throw new ResourceNotFoundException("Tipo de reporte no encontrado");
        }
            tipoReporteRepository.deleteById(idTipoReporte);
    }

}
