package cl.duoc.registro_session_service.controller;

import cl.duoc.registro_session_service.dto.RegistroSessionDTO;
import cl.duoc.registro_session_service.model.RegistroSession;
import cl.duoc.registro_session_service.service.RegistroSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/seguridades" )
public class RegistroSessionController {

    @Autowired
    private RegistroSessionService registroSessionService;

    @GetMapping
    public List<RegistroSessionDTO> findAll(){
        return registroSessionService.findAll();
    }

    @PostMapping
    public RegistroSession save(@RequestBody RegistroSession r) {
        return registroSessionService.save(r);
    }

    @GetMapping( "/usuario/{id}" )
    public List<RegistroSessionDTO> findAllByUsuario(@PathVariable("id") Long id){
        return registroSessionService.findAllByUsuario(id);
    }

    @GetMapping( "/anio" )
    public List<RegistroSessionDTO> findAllByAnio(@RequestParam(name = "anio") int anio){
        return registroSessionService.findAllByAnio(anio);
    }

    @GetMapping( "/mes" )
    public List<RegistroSessionDTO> findAllByMes(@RequestParam(name = "mes") int mes, @RequestParam(name = "anio") int anio){
        return registroSessionService.findAllByMes(mes, anio);
    }

    @GetMapping( "/dia" )
    public List<RegistroSessionDTO> findAllByDia(@RequestParam(name = "dia") int dia, @RequestParam(name = "mes") int mes, @RequestParam(name = "anio") int anio){
        return registroSessionService.findAllByDia(dia, mes, anio);
    }

    @GetMapping( "/entre-fechas" )
    public List<RegistroSessionDTO> findAllByEntreFechas(@RequestParam(name = "fecha-ini") String fecha_ini, @RequestParam(name = "fecha-ter") String fecha_ter){
        return registroSessionService.findAllByEntreFechas(fecha_ini, fecha_ter);
    }

}
