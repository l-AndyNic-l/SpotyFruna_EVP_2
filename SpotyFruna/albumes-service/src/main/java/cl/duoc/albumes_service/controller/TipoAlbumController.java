package cl.duoc.albumes_service.controller;

import cl.duoc.albumes_service.dto.TipoAlbumDTO;
import cl.duoc.albumes_service.model.Album;
import cl.duoc.albumes_service.model.TipoAlbum;
import cl.duoc.albumes_service.service.TipoAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/tipo-albumes" )
public class TipoAlbumController {

    @Autowired
    private TipoAlbumService tipoAlbumService;

    @GetMapping
    public List<TipoAlbumDTO> findAll() {
        return tipoAlbumService.findAll();
    }

    @GetMapping("/{id}")
    public TipoAlbumDTO findById( @PathVariable Long id ) {
        return tipoAlbumService.findById(id);
    }

    @PostMapping
    public TipoAlbum save(@RequestBody TipoAlbum a) {
        return tipoAlbumService.save(a);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable Long id, @RequestBody TipoAlbum ta) {
        return tipoAlbumService.update(ta, id);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return tipoAlbumService.deleteById(id);
    }

}
