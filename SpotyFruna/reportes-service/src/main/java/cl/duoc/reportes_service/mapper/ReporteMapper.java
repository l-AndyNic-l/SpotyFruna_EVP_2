package cl.duoc.reportes_service.mapper;

import cl.duoc.reportes_service.model.Reporte;
import cl.duoc.reportes_service.dto.ReporteDTO;
import cl.duoc.reportes_service.dto.UsuarioDTO;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Component
public class ReporteMapper {

    public ReporteDTO toDTO(Reporte reporte, UsuarioDTO administrador, UsuarioDTO usuario) {
        if(reporte == null) {
            return null;
        }

        ReporteDTO reporteDTO = new ReporteDTO();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        reporteDTO.setId(reporte.getId());
        reporteDTO.setAdministrador(administrador.getEmail());
        reporteDTO.setUsuario(usuario.getEmail());
        reporteDTO.setDescripcion(reporte.getDescripcion());
        reporteDTO.setFechaEnviado(reporte.getFechaEnviado().format(dtf));

        if(reporte.getFechaResuelto() != null) {
            reporteDTO.setFechaResuelto(reporte.getFechaResuelto().format(dtf));
        } else {
            reporteDTO.setFechaResuelto("No Resuelto");
        }

        reporteDTO.setTipoReporte(reporte.getTipoReporte().getNombre());
        reporteDTO.setEstado(reporte.getEstado().getNombre());

        return reporteDTO;
    }

}
