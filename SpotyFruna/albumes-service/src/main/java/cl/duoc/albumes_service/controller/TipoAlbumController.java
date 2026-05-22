package cl.duoc.albumes_service.controller;

import cl.duoc.albumes_service.model.TipoAlbum;
import cl.duoc.albumes_service.service.TipoAlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos_albumes")
public class TipoAlbumController {

    @Autowired
    private TipoAlbumService tipoAlbumService;



    @GetMapping
    public ResponseEntity<List<TipoAlbum>> findAll() {
        List<TipoAlbum> tiposAlbum = tipoAlbumService.findAll();

        if (tiposAlbum.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tiposAlbum);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAlbum> findById(@PathVariable Long id ) {
        TipoAlbum tipoAlbum = tipoAlbumService.findById(id);
        return ResponseEntity.ok(tipoAlbum);
    }

    @PostMapping
    public ResponseEntity<TipoAlbum> save(@Valid @RequestBody TipoAlbum a) {
        TipoAlbum tipoAlbum = tipoAlbumService.save(a);
        return new ResponseEntity<>(tipoAlbum, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoAlbum> update(@PathVariable Long id, @Valid @RequestBody TipoAlbum ta) {
        TipoAlbum tipoAlbum = tipoAlbumService.update(id, ta);
        return ResponseEntity.ok(tipoAlbum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoAlbumService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

