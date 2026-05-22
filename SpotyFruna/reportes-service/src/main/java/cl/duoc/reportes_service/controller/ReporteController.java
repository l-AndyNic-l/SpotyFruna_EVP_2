package cl.duoc.reportes_service.controller;

import cl.duoc.reportes_service.model.Reporte;
import cl.duoc.reportes_service.dto.ReporteDTO;
import cl.duoc.reportes_service.service.ReporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;



    @GetMapping
    public ResponseEntity<List<ReporteDTO>> findAll() {
        List<ReporteDTO> reportes = reporteService.findAll();

        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/administrador/{idAdministrador}")
    public ResponseEntity<List<ReporteDTO>> findAllByAdmin(@PathVariable Long idAdministrador) {
        List<ReporteDTO> reportes = reporteService.findAllByAdmin(idAdministrador);

        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReporteDTO>> findAllByUsuario(@PathVariable Long idUsuario) {
        List<ReporteDTO> reportes = reporteService.findAllByUsuario(idUsuario);

        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/estado")
    public ResponseEntity<List<ReporteDTO>> findAllByEstado(@RequestParam(name = "id-estado") Long idEstado) {
        List<ReporteDTO> reportes = reporteService.findAllByEstado(idEstado);

        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<ReporteDTO>> findAllBetweenDates(@RequestParam(name = "fecha-min") LocalDate fechaMin, @RequestParam(name = "fecha-max") LocalDate fechaMax) {
        List<ReporteDTO> reportes = reporteService.findAllBetweenDates(fechaMin, fechaMax);

        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<ReporteDTO> findOne(@PathVariable Long idReporte) {
        ReporteDTO reporte = reporteService.findById(idReporte);

        return ResponseEntity.ok(reporte);
    }

    @PostMapping
    public ResponseEntity<Reporte> save(@Valid @RequestBody Reporte reporte) {
        Reporte reporteInstanciado = reporteService.save(reporte);
        return new ResponseEntity<>(reporteInstanciado, HttpStatus.CREATED);
    }

    @PutMapping("/{idReporte}")
    public ResponseEntity<Reporte> update(@PathVariable Long idReporte, @Valid @RequestBody Reporte reporte) {
        Reporte reporteInstanciado = reporteService.update(idReporte, reporte);
        return ResponseEntity.ok(reporteInstanciado);
    }

    @DeleteMapping("/{idReporte}")
    public ResponseEntity<Void> deleteById(@PathVariable Long idReporte){
        reporteService.delete(idReporte);
        return ResponseEntity.noContent().build();
    }

}
