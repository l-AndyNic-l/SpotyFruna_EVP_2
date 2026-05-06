package cl.duoc.canciones_service.controller;

import cl.duoc.canciones_service.dto.GeneroDTO;
import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.model.Genero;
import cl.duoc.canciones_service.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/generos" )
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public List<GeneroDTO> findAll() {
        return generoService.findAll();
    }

    @GetMapping( "/{id}" )
    public GeneroDTO findById(@PathVariable Long id) {
        return generoService.findById(id);
    }

    @PostMapping
    public Genero save(@RequestBody Genero g) {
        return generoService.save(g);
    }

    @PutMapping( "/{id}" )
    public int update(@PathVariable Long id, @RequestBody Genero g) {
        return generoService.update(g, id);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return generoService.deleteById(id);
    }
}
