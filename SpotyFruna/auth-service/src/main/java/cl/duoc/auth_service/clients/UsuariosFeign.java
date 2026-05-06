package cl.duoc.auth_service.clients;

import cl.duoc.auth_service.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient( name = "usuarios", url = "http://localhost:8090/api/v1/usuarios")
public interface UsuariosFeign {

    @GetMapping
    List<UsuarioDTO> findAll();

    @GetMapping( "/{id}" )
    UsuarioDTO findById(@PathVariable Long id);

}
