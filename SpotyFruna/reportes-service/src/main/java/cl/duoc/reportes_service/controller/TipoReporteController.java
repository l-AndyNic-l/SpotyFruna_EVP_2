package cl.duoc.reportes_service.controller;

import cl.duoc.reportes_service.model.TipoReporte;
import cl.duoc.reportes_service.service.TipoReporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos_reportes")
public class TipoReporteController {

    @Autowired
    private TipoReporteService tipoReporteService;



    @GetMapping
    public ResponseEntity<List<TipoReporte>> findAll() {
        List<TipoReporte> tiposReporte = tipoReporteService.findAll();

        if (tiposReporte.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tiposReporte);
    }

    @GetMapping("/{tipoReporte}")
    public ResponseEntity<TipoReporte> findOne(@PathVariable String tipoReporte) {
        TipoReporte tipoReporteInstanciado = tipoReporteService.findByNombre(tipoReporte);

        return ResponseEntity.ok(tipoReporteInstanciado);
    }

    @PostMapping
    public ResponseEntity<TipoReporte> save(@Valid @RequestBody TipoReporte tipoReporte) {
        TipoReporte tipoReporteInstanciado = tipoReporteService.save(tipoReporte);
        return new ResponseEntity<>(tipoReporteInstanciado, HttpStatus.CREATED);
    }

    @PutMapping( "/{idTipoReporte}" )
    public ResponseEntity<TipoReporte> update(@PathVariable Long idTipoReporte, @Valid @RequestBody TipoReporte tipoReporte) {
        TipoReporte tipoReporteInstanciado = tipoReporteService.update(idTipoReporte, tipoReporte);
        return ResponseEntity.ok(tipoReporteInstanciado);
    }

    @DeleteMapping( "/{idTipoReporte}" )
    public ResponseEntity<TipoReporte> deleteById(@PathVariable Long idTipoReporte){
        tipoReporteService.deleteById(idTipoReporte);
        return ResponseEntity.noContent().build();
    }

}
