package cl.duoc.artistas_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/artistas" )
public class ArtistaController {

    @GetMapping
    public String getArtista () {
        return "Get + Artista";
    }

    @PostMapping
    public String postArtista () {
        return "Post + Artista";
    }

    @PutMapping( "/{id}" )
    public String putArtista ( @PathVariable Long id ) {
        return "Put + Artista + " + id;
    }

    @DeleteMapping( "/{id}" )
    public String deleteArtista ( @PathVariable Long id ) {
        return "Delete + Artista + " + id;
    }

}
