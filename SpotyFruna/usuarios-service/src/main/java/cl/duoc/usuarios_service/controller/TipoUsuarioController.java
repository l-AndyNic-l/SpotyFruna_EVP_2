package cl.duoc.usuarios_service.controller;

import cl.duoc.usuarios_service.model.TipoUsuario;
import cl.duoc.usuarios_service.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/tipos_usuarios" )
public class TipoUsuarioController {

    @Autowired
    TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public List<TipoUsuario> findAll() {
        return tipoUsuarioService.findAll();
    }

    @GetMapping( "/{tipo}" )
    public TipoUsuario findByTipo(@PathVariable  String tipo) {
        return tipoUsuarioService.findByTipo(tipo);
    }

    @PostMapping
    public TipoUsuario save(@RequestBody TipoUsuario tipoUsuario) {
        return tipoUsuarioService.save(tipoUsuario);
    }

    @DeleteMapping( "/{id}" )
    public Boolean deleteById(@PathVariable Long id){
        return tipoUsuarioService.deleteById(id);
    }



}
