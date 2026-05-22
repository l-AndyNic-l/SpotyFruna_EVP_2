package cl.duoc.usuarios_service.controller;

import cl.duoc.usuarios_service.model.TipoUsuario;
import cl.duoc.usuarios_service.service.TipoUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/tipos_usuarios" )
public class TipoUsuarioController {

    @Autowired
    TipoUsuarioService tipoUsuarioService;



    @GetMapping
    public ResponseEntity<List<TipoUsuario>> findAll() {
        List<TipoUsuario> tiposUsuario = tipoUsuarioService.findAll();

        if (tiposUsuario.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tiposUsuario);
    }

    @GetMapping( "/{tipo}" )
    public ResponseEntity<TipoUsuario> findOne(@PathVariable String tipo) {
        TipoUsuario tipoUsuario = tipoUsuarioService.findByNombre(tipo);

        return ResponseEntity.ok(tipoUsuario);
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> save(@Valid @RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario tipoUsuarioInstanciado = tipoUsuarioService.save(tipoUsuario);
        return new ResponseEntity<>(tipoUsuarioInstanciado, HttpStatus.CREATED);
    }

    @PutMapping( "/{idTipoUsuario}" )
    public ResponseEntity<TipoUsuario> update(@PathVariable Long idTipoUsuario, @Valid @RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario tipoUsuarioInstanciado = tipoUsuarioService.update(idTipoUsuario, tipoUsuario);
        return ResponseEntity.ok(tipoUsuarioInstanciado);
    }

    @DeleteMapping( "/{idTipoUsuario}" )
    public ResponseEntity<Void> deleteById(@PathVariable Long idTipoUsuario){
        tipoUsuarioService.deleteById(idTipoUsuario);
        return ResponseEntity.noContent().build();
    }

}
