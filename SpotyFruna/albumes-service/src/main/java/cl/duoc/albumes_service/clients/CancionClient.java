package cl.duoc.albumes_service.clients;

import cl.duoc.albumes_service.dto.AlbumCancionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "canciones-service")
public interface CancionClient {

    @GetMapping("/api/v1/canciones/album/{idAlbum}")
    List<AlbumCancionDTO> findAllByIdAlbum(@PathVariable Long idAlbum);

}
