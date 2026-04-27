package cl.duoc.clientes_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/clientes" )
public class ClienteController {

    @GetMapping
    public String getCliente () {
        return "Get + Cliente";
    }

    @PostMapping
    public String postCliente () {
        return "Post + Cliente";
    }

    @PutMapping( "/{id}" )
    public String putCliente ( @PathVariable Long id ) {
        return "Put + Cliente + " + id;
    }

    @DeleteMapping( "/{id}" )
    public String deleteCliente ( @PathVariable Long id ) {
        return "Delete + Cliente + " + id;
    }

}
