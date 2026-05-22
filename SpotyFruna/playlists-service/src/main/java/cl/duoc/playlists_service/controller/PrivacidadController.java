package cl.duoc.playlists_service.controller;

import cl.duoc.playlists_service.model.Privacidad;
import cl.duoc.playlists_service.service.PrivacidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api/v1/privacidades_playlists" )
public class PrivacidadController {

    @Autowired
    private PrivacidadService privacidadService;



    @GetMapping
    public ResponseEntity<List<Privacidad>> findAll() {
        List<Privacidad> privacidades = privacidadService.findAll();

        if (privacidades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(privacidades);
    }

    @GetMapping( "/{idPrivacidad}" )
    public ResponseEntity<Privacidad> findById(@PathVariable Long idPrivacidad) {
        Privacidad privacidad = privacidadService.findById(idPrivacidad);

        return ResponseEntity.ok(privacidad);
    }

    @PostMapping
    public ResponseEntity<Privacidad> save(@Valid @RequestBody Privacidad privacidad) {
        Privacidad privacidadInstanciada = privacidadService.save(privacidad);
        return new ResponseEntity<>(privacidadInstanciada, HttpStatus.CREATED);
    }

    @PutMapping( "/{idPrivacidad}" )
    public ResponseEntity<Privacidad> update(@PathVariable Long idPrivacidad, @Valid @RequestBody Privacidad privacidad) {
        Privacidad privacidadInstanciada = privacidadService.update(idPrivacidad, privacidad);
        return ResponseEntity.ok(privacidadInstanciada);
    }

    @DeleteMapping("/{idPrivacidad}")
    public ResponseEntity<Void> delete(@PathVariable Long idPrivacidad) {
        privacidadService.deleteById(idPrivacidad);
        return ResponseEntity.noContent().build();
    }

}
