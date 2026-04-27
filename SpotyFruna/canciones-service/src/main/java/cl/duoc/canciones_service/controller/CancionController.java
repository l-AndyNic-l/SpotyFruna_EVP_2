package cl.duoc.canciones_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/canciones" )
public class CancionController {

    @GetMapping
    public String getCancion () {
        return "Get + Cancion";
    }

    @PostMapping
    public String postCancion () {
        return "Post + Cancion";
    }

    @PutMapping( "/{id}" )
    public String putCancion ( @PathVariable Long id ) {
        return "Put + Cancion + " + id;
    }

    @DeleteMapping( "/{id}" )
    public String deleteCancion ( @PathVariable Long id ) {
        return "Delete + Cancion + " + id;
    }

}
