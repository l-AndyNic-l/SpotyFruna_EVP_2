package cl.duoc.auth_service.controller;

import cl.duoc.auth_service.dto.AuthDTO;
import cl.duoc.auth_service.model.Auth;
import cl.duoc.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/registros" )
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<AuthDTO> findAll(){
        return authService.findAll();
    }

    @PostMapping
    public Auth save(@RequestBody Auth r) {
        return authService.save(r);
    }

    @GetMapping( "/usuario/{id}" )
    public List<AuthDTO> findAllByUsuario(@PathVariable("id") Long id){
        return authService.findAllByUsuario(id);
    }

    @GetMapping( "/anio" )
    public List<AuthDTO> findAllByAnio(@RequestParam(name = "anio") int anio){
        return authService.findAllByAnio(anio);
    }

    @GetMapping( "/mes" )
    public List<AuthDTO> findAllByMes(@RequestParam(name = "mes") int mes, @RequestParam(name = "anio") int anio){
        return authService.findAllByMes(mes, anio);
    }

    @GetMapping( "/dia" )
    public List<AuthDTO> findAllByDia(@RequestParam(name = "dia") int dia, @RequestParam(name = "mes") int mes, @RequestParam(name = "anio") int anio){
        return authService.findAllByDia(dia, mes, anio);
    }

    @GetMapping( "/entre-fechas" )
    public List<AuthDTO> findAllByEntreFechas(@RequestParam(name = "fecha-ini") String fecha_ini, @RequestParam(name = "fecha-ter") String fecha_ter){
        return authService.findAllByEntreFechas(fecha_ini, fecha_ter);
    }

}
