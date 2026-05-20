package cl.duoc.reproducciones_service.controller;

import cl.duoc.reproducciones_service.model.Dispositivo;
import cl.duoc.reproducciones_service.service.DispositivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dispositivos_reproducciones")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;



    @GetMapping
    public ResponseEntity<List<Dispositivo>> findAll() {
        List<Dispositivo> dispositivos = dispositivoService.findAll();

        if (dispositivos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dispositivos);
    }

    @GetMapping( "/{nombre}" )
    public ResponseEntity<Dispositivo> findByNombre(@PathVariable String nombre) {
        Dispositivo dispositivo = dispositivoService.findByNombre(nombre);

        return ResponseEntity.ok(dispositivo);
    }

    @PostMapping
    public ResponseEntity<Dispositivo> save(@Valid @RequestBody Dispositivo dispositivo) {
        Dispositivo dispositivoInstanciado = dispositivoService.save(dispositivo);
        return new ResponseEntity<>(dispositivoInstanciado, HttpStatus.CREATED);
    }

    @PutMapping( "/{idDispositivo}" )
    public ResponseEntity<Dispositivo> update(@PathVariable Long idDispositivo, @Valid @RequestBody Dispositivo dispositivo) {
        Dispositivo dispositivoInstanciado = dispositivoService.update(idDispositivo, dispositivo);
        return ResponseEntity.ok(dispositivoInstanciado);
    }

    @DeleteMapping( "/{idDispositivo}" )
    public ResponseEntity<Void> delete(@PathVariable Long idDispositivo) {
        dispositivoService.delete(idDispositivo);
        return ResponseEntity.noContent().build();
    }


}
