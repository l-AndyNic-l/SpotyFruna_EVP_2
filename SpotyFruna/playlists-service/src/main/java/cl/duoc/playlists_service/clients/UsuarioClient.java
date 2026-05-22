package cl.duoc.playlists_service.clients;

import cl.duoc.playlists_service.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "usuarios-service")
public interface UsuarioClient {

    @GetMapping( "/api/v1/usuarios/{idUsuario}" )
    UsuarioDTO findById(@PathVariable Long idUsuario);

}
