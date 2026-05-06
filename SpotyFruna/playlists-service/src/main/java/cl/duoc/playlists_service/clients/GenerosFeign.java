package cl.duoc.playlists_service.clients;

import cl.duoc.playlists_service.dto.GeneroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "generos", url = "http://localhost:8095/api/v1/generos")
public interface GenerosFeign {

    @GetMapping
    List<GeneroDTO> findAll();

    @GetMapping( "/{id}" )
    GeneroDTO findById(@PathVariable Long id);

}
