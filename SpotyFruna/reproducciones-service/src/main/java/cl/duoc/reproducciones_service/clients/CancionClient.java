package cl.duoc.reproducciones_service.clients;

import cl.duoc.reproducciones_service.dto.CancionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "canciones-service")
public interface CancionClient {

    @GetMapping("/api/v1/canciones/{idCancion}")
    CancionDTO findById( @PathVariable Long idCancion);

}
