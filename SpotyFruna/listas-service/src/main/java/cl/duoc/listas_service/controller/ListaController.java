package cl.duoc.listas_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/listas" )
public class ListaController {

    @GetMapping
    public String getLista () {
        return "Get + Lista";
    }

    @PostMapping
    public String postLista () {
        return "Post + Lista";
    }

    @PutMapping( "/{id}" )
    public String putLista ( @PathVariable Long id ) {
        return "Put + Lista + " + id;
    }

    @DeleteMapping( "/{id}" )
    public String deleteLista ( @PathVariable Long id ) {
        return "Delete + Lista + " + id;
    }


}
