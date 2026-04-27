package cl.duoc.contenido_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/contenido" )
public class ContenidoController {

    @GetMapping
    public String getContenido () {
        return "Get + Contenido";
    }

    @PostMapping
    public String postContenido () {
        return "Post + Contenido";
    }

    @PutMapping( "/{id}" )
    public String putContenido ( @PathVariable Long id ) {
        return "Put + Contenido + " + id;
    }

    @DeleteMapping( "/{id}" )
    public String deleteContenido ( @PathVariable Long id ) {
        return "Delete + Contenido + " + id;
    }

}
