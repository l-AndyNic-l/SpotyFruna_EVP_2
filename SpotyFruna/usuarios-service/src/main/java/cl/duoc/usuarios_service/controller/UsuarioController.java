package cl.duoc.usuarios_service.controller;

import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/usuarios" )
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;



    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> usuarios = usuarioService.findAll();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/tipo-usuario/{idTipoUsuario}")
    public ResponseEntity<List<UsuarioDTO>> findAllByTipoUsuario(@PathVariable Long idTipoUsuario) {
        List<UsuarioDTO> usuarios = usuarioService.findAllByTipoUsuario(idTipoUsuario);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/nickname")
    public ResponseEntity<List<UsuarioDTO>> findAllByNickname(@RequestParam(name = "nickname") String nickname) {
        List<UsuarioDTO> usuarios = usuarioService.findAllByNickname(nickname);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/between-dates")
    ResponseEntity<List<UsuarioDTO>> findAllBetweenDates(@RequestParam(name = "fecha-min") LocalDate fechaMin, @RequestParam(name = "fecha-max") LocalDate fechaMax) {
        List<UsuarioDTO> usuarios = usuarioService.findAllBetweenDates(fechaMin, fechaMax);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping( "/{idUsuario}" )
    public ResponseEntity<UsuarioDTO> findById( @PathVariable Long idUsuario) {
        UsuarioDTO usuario = usuarioService.findById(idUsuario);

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioInstanciado = usuarioService.save(usuario);
        return new ResponseEntity<>(usuarioInstanciado, HttpStatus.CREATED);
    }

    @PutMapping( "/{idUsuario}" )
    public ResponseEntity<Usuario> update( @PathVariable Long idUsuario, @Valid @RequestBody Usuario usuario) {
        Usuario usuarioInstanciado = usuarioService.update(idUsuario, usuario);
        return ResponseEntity.ok(usuarioInstanciado);
    }

    @DeleteMapping( "/{idUsuario}" )
    public ResponseEntity<Void> delete(@PathVariable Long idUsuario) {
        usuarioService.deleteById(idUsuario);
        return ResponseEntity.noContent().build();
    }

}
