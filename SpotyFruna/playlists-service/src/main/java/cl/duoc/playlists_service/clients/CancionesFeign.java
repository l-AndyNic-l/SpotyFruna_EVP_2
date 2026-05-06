package cl.duoc.playlists_service.clients;

import cl.duoc.playlists_service.dto.CancionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "canciones", url = "http://localhost:8095/api/v1/canciones")
public interface CancionesFeign {

    @GetMapping
    List<CancionDTO> findAll();

    @GetMapping( "/{id}" )
    CancionDTO findById(@PathVariable Long id);

}
