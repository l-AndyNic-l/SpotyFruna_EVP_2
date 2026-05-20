package cl.duoc.canciones_service.clients;

import cl.duoc.canciones_service.dto.AlbumDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "albumes-service")
public interface AlbumClient {

    @GetMapping("/api/v1/albumes/{idAlbum}")
    AlbumDTO findById(@PathVariable Long idAlbum);

}
