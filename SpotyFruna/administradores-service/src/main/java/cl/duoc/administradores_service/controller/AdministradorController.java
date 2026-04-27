package cl.duoc.administradores_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/administradores" )
public class AdministradorController {

    @GetMapping
    public String getAdministrador () {
        return "Get + Administrador";
    }

    @PostMapping
    public String postAdministrador () {
        return "Post + Administrador";
    }

    @PutMapping( "/{id}" )
    public String putAdministrador ( @PathVariable Long id ) {
        return "Put + Administrador + " + id;
    }

    @DeleteMapping( "/{id}" )
    public String deleteAdministrador ( @PathVariable Long id ) {
        return "Delete + Administrador + " + id;
    }

}
