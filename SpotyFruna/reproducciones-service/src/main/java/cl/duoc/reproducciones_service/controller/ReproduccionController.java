package cl.duoc.reproducciones_service.controller;

import cl.duoc.reproducciones_service.model.Reproduccion;
import cl.duoc.reproducciones_service.dto.ReproduccionDTO;
import cl.duoc.reproducciones_service.service.ReproduccionService;
import feign.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/reproducciones" )
public class ReproduccionController {

    @Autowired
    private ReproduccionService reproduccionService;



    @GetMapping
    public ResponseEntity<List<ReproduccionDTO>> findAll() {
        List<ReproduccionDTO> reproducciones = reproduccionService.findAll();

        if (reproducciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reproducciones);
    }

    @GetMapping("/{idReproduccion}")
    public ResponseEntity<ReproduccionDTO> findById(@PathVariable Long idReproduccion) {
        ReproduccionDTO reproduccion = reproduccionService.findById(idReproduccion);

        return ResponseEntity.ok(reproduccion);
    }

    @GetMapping("/dispositivo/{idDispositivo}")
    public ResponseEntity<List<ReproduccionDTO>> findAllByDispositivo(@PathVariable Long idDispositivo) {
        List<ReproduccionDTO> reproducciones = reproduccionService.findAllByDispositivo(idDispositivo);

        if (reproducciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reproducciones);
    }

    @GetMapping("/cancion")
    public ResponseEntity<List<ReproduccionDTO>> findAllByCancion(@RequestParam Long idCancion) {
        List<ReproduccionDTO> reproducciones = reproduccionService.findAllByCancion(idCancion);

        if (reproducciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reproducciones);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReproduccionDTO>> findAllByUsuario(@PathVariable Long idUsuario) {
        List<ReproduccionDTO> reproducciones = reproduccionService.findAllByUsuario(idUsuario);

        if (reproducciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reproducciones);
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<ReproduccionDTO>> findAllBetweenDates(@RequestParam(name = "fecha-min") LocalDate fechaMin, @RequestParam(name = "fecha-max") LocalDate fechaMax) {
        List<ReproduccionDTO> reproducciones = reproduccionService.findAllBetweenDates(fechaMin, fechaMax);

        if (reproducciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reproducciones);
    }

    @PostMapping
    public ResponseEntity<Reproduccion> save(@Valid @RequestBody Reproduccion reproduccion) {
        Reproduccion reproducionInstanciada = reproduccionService.save(reproduccion);
        return new ResponseEntity<>(reproducionInstanciada, HttpStatus.CREATED);
    }

    @PutMapping("/{idReproduccion}")
    public ResponseEntity<Reproduccion> update(@PathVariable Long id, @Valid @RequestBody Reproduccion r) {
        Reproduccion reproduccion = reproduccionService.update(id, r);
        return ResponseEntity.ok(reproduccion);
    }

    @DeleteMapping("/{idReproduccion}")
    public ResponseEntity<Void> delete(@PathVariable Long idReproduccion) {
        reproduccionService.delete(idReproduccion);
        return ResponseEntity.noContent().build();
    }

}
