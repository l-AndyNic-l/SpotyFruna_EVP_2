package cl.duoc.albumes_service.controller;

import cl.duoc.albumes_service.dto.AlbumDTO;
import cl.duoc.albumes_service.model.Album;
import cl.duoc.albumes_service.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/albumes")
public class AlbumController {

    @Autowired
    private AlbumService albumService;


    @GetMapping
    public ResponseEntity<List<AlbumDTO>> findAll() {
        List<AlbumDTO> albums = albumService.findAll();

        if (albums.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(albums);
    }

    @GetMapping("/tipo-album/{idTipoAlbum}")
    public ResponseEntity<List<AlbumDTO>> findAllByTipoAlbum(@PathVariable Long idTipoAlbum) {
        List<AlbumDTO> albums = albumService.findAllByTipoAlbum(idTipoAlbum);

        if (albums.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(albums);
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<AlbumDTO>> findAllBetweenDates(@RequestParam(name = "fecha-min") LocalDate fechaMin, @RequestParam(name = "fecha-max") LocalDate fechaMax) {
        List<AlbumDTO> albums = albumService.findAllBetweenDates(fechaMin, fechaMax);

        if (albums.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(albums);
    }

    @GetMapping("/artista/{idArtista}")
    public ResponseEntity<List<AlbumDTO>> findAllByArtist(@PathVariable Long idArtista) {
        List<AlbumDTO> albums = albumService.findAllByArtist(idArtista);

        if (albums.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{idAlbum}")
    public ResponseEntity<AlbumDTO> findById(@PathVariable Long idAlbum) {
        AlbumDTO album = albumService.findById(idAlbum);
        return ResponseEntity.ok(album);
    }

    @PostMapping
    public ResponseEntity<Album> save(@Valid @RequestBody Album album) {
        Album albumInstanciado = albumService.save(album);
        return new ResponseEntity<>(albumInstanciado, HttpStatus.CREATED);
    }

    @PutMapping("/{idAlbum}")
    public ResponseEntity<Album> update(@PathVariable Long idAlbum, @Valid @RequestBody Album album) {
        Album albumInstanciado = albumService.update(idAlbum, album);
        return ResponseEntity.ok(albumInstanciado);
    }

    @DeleteMapping("/{idAlbum}")
    public ResponseEntity<Void> delete(@PathVariable Long idAlbum) {
        albumService.deleteById(idAlbum);
        return ResponseEntity.noContent().build();
    }

}
