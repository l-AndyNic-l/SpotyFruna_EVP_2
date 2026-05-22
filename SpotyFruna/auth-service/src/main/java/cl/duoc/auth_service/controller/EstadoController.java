package cl.duoc.auth_service.controller;

import cl.duoc.auth_service.model.Estado;
import cl.duoc.auth_service.service.EstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/estados_registros" )
public class EstadoController {

    @Autowired
    private EstadoService estadoService;



    @GetMapping
    public ResponseEntity<List<Estado>> findAll() {
        List<Estado> estados = estadoService.findAll();

        if (estados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(estados);
    }

    @GetMapping( "/{tipo}" )
    public ResponseEntity<Estado> findOne(@PathVariable String tipo) {
        Estado estadoInstanciado = estadoService.findByNombre(tipo);
        return ResponseEntity.ok(estadoInstanciado);
    }

    @PostMapping
    public ResponseEntity<Estado> save(@Valid @RequestBody Estado estado) {
        Estado estadoInstanciado = estadoService.save(estado);
        return new ResponseEntity<>(estadoInstanciado, HttpStatus.CREATED);
    }

    @PutMapping( "/{idEstado}" )
    public ResponseEntity<Estado> update(@PathVariable Long idEstado, @Valid @RequestBody Estado estado) {
        Estado estadoInstanciado = estadoService.update(idEstado, estado);
        return ResponseEntity.ok(estadoInstanciado);
    }

    @DeleteMapping( "/{idEstado}" )
    public ResponseEntity<Void> deleteById(@PathVariable Long idEstado){
        estadoService.deleteById(idEstado);
        return ResponseEntity.noContent().build();
    }

}
