package cl.duoc.usuarios_service.controller;

import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/usuarios" )
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping( "/{id}" )
    public UsuarioDTO findById( @PathVariable  Long id ) {
        return usuarioService.findById(id);
    }

    @PostMapping
    public Usuario save( @RequestBody Usuario u ) {
        return usuarioService.save(u);
    }

    @PutMapping( "/{id}" )
    public int update( @PathVariable  Long id, @RequestBody Usuario u ) {
        return usuarioService.update(u, id);
    }

    @DeleteMapping( "/{id}" )
    public Boolean delete( @PathVariable  Long id ) {
        return usuarioService.deleteById(id);
    }

}
