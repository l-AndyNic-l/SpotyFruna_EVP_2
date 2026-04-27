package cl.duoc.soportes_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/soportes" )
public class SoportesController {

    @GetMapping
    public String getSoporte () {
        return "Get + Soporte";
    }

    @PostMapping
    public String postSoporte () {
        return "Post + Soporte";
    }

    @PutMapping( "/{id}" )
    public String putSoporte ( @PathVariable Long id ) {
        return "Put + Soporte + " + id;
    }

    @DeleteMapping( "/{id}" )
    public String deleteSoporte ( @PathVariable Long id ) {
        return "Delete + Soporte + " + id;
    }

}
