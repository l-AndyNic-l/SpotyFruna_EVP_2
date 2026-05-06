package cl.duoc.albumes_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/albumes" )
public class AlbumController {

    @GetMapping
    public String getAlbum () {
        return "Get + Album";
    }

    @PostMapping
    public String postAlbum () {
        return "Post + Album";
    }

    @PutMapping( "/{id}" )
    public String putAlbum ( @PathVariable Long id ) {
        return "Put + Album + " + id;
    }

    @DeleteMapping( "/{id}" )
    public String deleteAlbum ( @PathVariable Long id ) {
        return "Delete + Album + " + id;
    }

}
