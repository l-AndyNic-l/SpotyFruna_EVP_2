package cl.duoc.canciones_service.controller;

import cl.duoc.canciones_service.model.Genero;
import cl.duoc.canciones_service.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/generos_canciones")
public class GeneroController {

    @Autowired
    private GeneroService generoService;



    @GetMapping
    public ResponseEntity<List<Genero>> findAll() {
        List<Genero> generos = generoService.findAll();

        if (generos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(generos);
    }

    @GetMapping("/{idGenero}")
    public ResponseEntity<Genero> findById(@PathVariable Long idGenero) {
        Genero genero = generoService.findById(idGenero);

        return ResponseEntity.ok(genero);
    }

    @PostMapping
    public ResponseEntity<Genero> save(@Valid @RequestBody Genero genero) {
        Genero generoInstanciado = generoService.save(genero);
        return new ResponseEntity<>(generoInstanciado, HttpStatus.CREATED);
    }

    @PutMapping("/{idGenero}")
    public ResponseEntity<Genero> update(@PathVariable Long idGenero, @Valid @RequestBody Genero genero) {
        Genero generoInstanciado = generoService.update(idGenero, genero);
        return ResponseEntity.ok(generoInstanciado);
    }

    @DeleteMapping("/{idGenero}")
    public ResponseEntity<Void> delete(@PathVariable  Long idGenero) {
        generoService.deleteById(idGenero);
        return ResponseEntity.noContent().build();
    }

}
