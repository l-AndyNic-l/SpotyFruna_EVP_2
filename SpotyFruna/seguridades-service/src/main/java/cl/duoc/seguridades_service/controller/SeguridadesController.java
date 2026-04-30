package cl.duoc.seguridades_service.controller;

import cl.duoc.seguridades_service.clients.UsuariosFeign;
import cl.duoc.seguridades_service.dto.UsuarioDTO;
import cl.duoc.seguridades_service.model.RegistroSession;
import cl.duoc.seguridades_service.service.RegistroSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/seguridades" )
public class SeguridadesController {

    @Autowired
    private RegistroSessionService registroSessionService;

    @Autowired
    private UsuariosFeign usuarios;

    @GetMapping( "/usuarios" )
    public List<UsuarioDTO> listadoUsuarios () {
        return usuarios.findAll();
    }

    @GetMapping( "/usuarios/{id}" )
    public UsuarioDTO usuario (@PathVariable  Long id) {
        return usuarios.findById(id);
    }

    @PostMapping
    public RegistroSession save(@RequestBody RegistroSession r) {
        return registroSessionService.save(r);
    }

}
