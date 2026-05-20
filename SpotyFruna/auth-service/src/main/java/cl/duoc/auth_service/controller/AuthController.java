package cl.duoc.auth_service.controller;

import cl.duoc.auth_service.dto.AuthDTO;
import cl.duoc.auth_service.model.Auth;
import cl.duoc.auth_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/registros")
public class AuthController {

    @Autowired
    private AuthService authService;



    @GetMapping
    public ResponseEntity<List<AuthDTO>> findAll(){
         List<AuthDTO> auths = authService.findAll();

         if (auths.isEmpty()) {
             return ResponseEntity.noContent().build();
         }

         return ResponseEntity.ok(auths);
    }

    @GetMapping( "/usuario/{idUsuario}" )
    public ResponseEntity<List<AuthDTO>> findAllByUsuario(@PathVariable("idUsuario") Long idUsuario){
        List<AuthDTO> auths = authService.findAllByUsuario(idUsuario);

        if (auths.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(auths);
    }

    @GetMapping( "/anio" )
    public ResponseEntity<List<AuthDTO>> findAllByAnio(@RequestParam(name = "anio") int anio){
        List<AuthDTO> auths = authService.findAllByAnio(anio);

        if (auths.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(auths);
    }

    @GetMapping( "/mes" )
    public ResponseEntity<List<AuthDTO>> findAllByMes(@RequestParam(name = "mes") int mes, @RequestParam(name = "anio") int anio){
        List<AuthDTO> auths = authService.findAllByMes(mes, anio);

        if (auths.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(auths);
    }

    @GetMapping( "/dia" )
    public ResponseEntity<List<AuthDTO>> findAllByDia(@RequestParam(name = "dia") int dia, @RequestParam(name = "mes") int mes, @RequestParam(name = "anio") int anio){
        List<AuthDTO> auths = authService.findAllByDia(dia, mes, anio);

        if (auths.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(auths);
    }

    @GetMapping( "/entre-fechas" )
    public ResponseEntity<List<AuthDTO>> findAllByEntreFechas(@RequestParam(name = "fecha-ini") String fecha_ini, @RequestParam(name = "fecha-ter") String fecha_ter){
        List<AuthDTO> auths = authService.findAllByEntreFechas(fecha_ini, fecha_ter);

        if (auths.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(auths);
    }

    @PostMapping
    public ResponseEntity<Auth> save(@Valid @RequestBody Auth r) {
        Auth authInstanciado = authService.save(r);
        return new ResponseEntity<>(authInstanciado, HttpStatus.CREATED);
    }

    @PutMapping( "/{idAuth}" )
    public ResponseEntity<Auth> update(@PathVariable Long idAuth, @Valid @RequestBody Auth auth) {
        Auth authInstanciado = authService.update(idAuth, auth);
        return ResponseEntity.ok(authInstanciado);
    }

}
