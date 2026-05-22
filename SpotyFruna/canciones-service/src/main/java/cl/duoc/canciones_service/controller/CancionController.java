package cl.duoc.canciones_service.controller;

import cl.duoc.canciones_service.dto.AlbumCancionDTO;
import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.dto.CancionDTO;
import cl.duoc.canciones_service.service.CancionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/canciones")
public class CancionController {

    @Autowired
    private CancionService cancionService;



    @GetMapping
    public ResponseEntity<List<CancionDTO>> findAll() {
        List<CancionDTO> canciones = cancionService.findAll();

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }

    @GetMapping( "/{idCancion}" )
    public ResponseEntity<CancionDTO> findById(@PathVariable Long idCancion) {
        CancionDTO cancion = cancionService.findById(idCancion);

        return ResponseEntity.ok(cancion);
    }

    @GetMapping("/album/{idAlbum}")
    public ResponseEntity<List<AlbumCancionDTO>> findAllByIdAlbum(@PathVariable Long idAlbum) {
        List<AlbumCancionDTO> cancionesAlbum = cancionService.findAllByIdAlbum(idAlbum);

        if (cancionesAlbum.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cancionesAlbum);
    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<CancionDTO>> findAllByAutor(@PathVariable String autor) {
        List<CancionDTO> canciones = cancionService.findAllByAutor(autor);

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }

    @GetMapping("/genero/{idGenero}")
    public ResponseEntity<List<CancionDTO>> findAllByGenero(@PathVariable Long idGenero) {
        List<CancionDTO> canciones = cancionService.findAllByGenero(idGenero);

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<CancionDTO>> findAllBetweenDates(@RequestParam(name = "fecha-min") LocalDate fechaMin, @RequestParam(name = "fecha-max") LocalDate fechaMax) {
        List<CancionDTO> canciones = cancionService.findAllBetweenDates(fechaMin, fechaMax);

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }

    @GetMapping("/between-durations")
    public ResponseEntity<List<CancionDTO>> findAllBetweenDuration(@RequestParam(name = "duracion-min") Long duracionMin, @RequestParam(name = "duracion-max") Long duracionMax) {
        List<CancionDTO> canciones = cancionService.findAllBetweenDuration(duracionMin, duracionMax);

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }

    @PostMapping
    public ResponseEntity<Cancion> save(@Valid @RequestBody Cancion cancion) {
        Cancion cancionInstanciada = cancionService.save(cancion);
        return new ResponseEntity<>(cancionInstanciada, HttpStatus.CREATED);
    }

    @PutMapping( "/{idCancion}" )
    public ResponseEntity<Cancion> update(@Valid @PathVariable Long idCancion, @RequestBody Cancion cancion) {
        Cancion cancionInstanciada = cancionService.update(idCancion, cancion);
        return ResponseEntity.ok(cancionInstanciada);
    }

    @DeleteMapping( "/{idCancion}" )
    public ResponseEntity<Void> delete(@PathVariable  Long idCancion) {
        cancionService.deleteById(idCancion);
        return ResponseEntity.noContent().build();
    }

}
