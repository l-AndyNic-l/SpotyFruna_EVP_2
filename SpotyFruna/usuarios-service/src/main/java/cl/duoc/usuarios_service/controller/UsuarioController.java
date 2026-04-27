package cl.duoc.usuarios_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/usuarios" )
public class UsuarioController {

    @GetMapping
    public String getUsuarios () {
        return "Get + Usuario";
    }

    @PostMapping
    public String postUsuario () {
        return "Post + Usuario";
    }

    @PutMapping( "/{id}" )
    public String putUsuario ( @PathVariable Long id ) {
        return "Put + Usuario + " + id;
    }

    @DeleteMapping( "/{id}" )
    public String deleteUsuario ( @PathVariable Long id ) {
        return "Delete + Usuario + " + id;
    }

}
