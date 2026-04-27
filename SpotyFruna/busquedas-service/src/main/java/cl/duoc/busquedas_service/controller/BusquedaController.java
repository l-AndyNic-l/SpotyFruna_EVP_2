package cl.duoc.busquedas_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/busquedas" )
public class BusquedaController {

    @GetMapping
    public String getBusqueda () {
        return "Get + Busqueda";
    }

    @PostMapping
    public String postBusqueda () {
        return "Post + Busqueda";
    }

    @PutMapping( "/{id}" )
    public String putBusqueda ( @PathVariable Long id ) {
        return "Put + Busqueda + " + id;
    }

    @DeleteMapping( "/{id}" )
    public String deteleBusqueda ( @PathVariable Long id ) {
        return "Delete + Busqueda + " + id;
    }

}
