package cl.duoc.suscripciones_service.controller;

import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.model.Suscripcion;
import cl.duoc.suscripciones_service.service.SuscripcionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/suscripciones")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;



    @GetMapping
    public ResponseEntity<List<SuscripcionDTO>> findAll() {
        List<SuscripcionDTO> suscripciones = suscripcionService.findAll();

        if (suscripciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(suscripciones);
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<SuscripcionDTO>> findAllBetweenDates(@RequestParam(name = "fecha-min") LocalDate fechaMin, @RequestParam(name = "fecha-max") LocalDate fechaMax) {
        List<SuscripcionDTO> suscripciones = suscripcionService.findAllBetweenDates(fechaMin, fechaMax);

        if (suscripciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(suscripciones);
    }

    @GetMapping("/plan/{idPlan}")
    public ResponseEntity<List<SuscripcionDTO>> findAllByPlan(@PathVariable Long idPlan) {
        List<SuscripcionDTO> suscripciones = suscripcionService.findAllByPlan(idPlan);

        if (suscripciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(suscripciones);
    }

    @GetMapping("/activado")
    public ResponseEntity<List<SuscripcionDTO>> findAllByActivado(@RequestParam(name = "activado") Boolean activado) {
        List<SuscripcionDTO> suscripciones = suscripcionService.findAllByActivado(activado);

        if (suscripciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(suscripciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuscripcionDTO> findById(@PathVariable Long idSuscripcion) {
        SuscripcionDTO suscripcion = suscripcionService.findById(idSuscripcion);

        return ResponseEntity.ok(suscripcion);
    }

    @PostMapping
    public ResponseEntity<Suscripcion> save(@Valid @RequestBody Suscripcion suscripcion) {
        Suscripcion suscripcionInstanciada = suscripcionService.save(suscripcion);
        return new ResponseEntity<>(suscripcionInstanciada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Suscripcion> update(@PathVariable Long idSuscripcion, @Valid @RequestBody Suscripcion suscripcion ) {
        Suscripcion suscripcionInstanciada = suscripcionService.update(idSuscripcion, suscripcion);
        return ResponseEntity.ok(suscripcionInstanciada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long idSuscripcion) {
        suscripcionService.deleteById(idSuscripcion);
        return ResponseEntity.noContent().build();
    }

}
