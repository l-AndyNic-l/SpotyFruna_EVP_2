package cl.duoc.playlists_service.controller;

import cl.duoc.playlists_service.dto.GuardarCancionDTO;
import cl.duoc.playlists_service.model.GuardarCancion;
import cl.duoc.playlists_service.service.GuardarCancionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guardar_canciones_playlists")
public class GuardarCancionController {

    @Autowired
    private GuardarCancionService guardarCancionService;



    @GetMapping
    public ResponseEntity<List<GuardarCancionDTO>> findAll() {
        List<GuardarCancionDTO> cancionesGuardadas = guardarCancionService.findAll();

        if (cancionesGuardadas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cancionesGuardadas);
    }

    @GetMapping( "/{idGuardarCancion}" )
    public ResponseEntity<GuardarCancionDTO> findById(@PathVariable Long idGuardarCancion) {
        GuardarCancionDTO cancionGuardada = guardarCancionService.findById(idGuardarCancion);

        return ResponseEntity.ok(cancionGuardada);
    }

    @PostMapping
    public ResponseEntity<GuardarCancion> save(@Valid @RequestBody GuardarCancion guardarCancion) {
        GuardarCancion cancionGuardada = guardarCancionService.save(guardarCancion);
        return new ResponseEntity<>(cancionGuardada, HttpStatus.CREATED);
    }

    @PutMapping( "/{idGuardarCancion}" )
    public ResponseEntity<GuardarCancion> update(@PathVariable Long idGuardarCancion, @Valid @RequestBody GuardarCancion guardarCancion) {
        GuardarCancion cancionGuardada = guardarCancionService.update(idGuardarCancion, guardarCancion);
        return ResponseEntity.ok(cancionGuardada);
    }

    @DeleteMapping("/{idGuardarCancion}")
    public ResponseEntity<Void> delete(@PathVariable Long idGuardarCancion) {
        guardarCancionService.deleteById(idGuardarCancion);
        return ResponseEntity.noContent().build();
    }
}
