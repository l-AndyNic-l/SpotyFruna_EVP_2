package cl.duoc.albumes_service.controller;

import cl.duoc.albumes_service.dto.AlbumDTO;
import cl.duoc.albumes_service.model.Album;
import cl.duoc.albumes_service.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/albumes" )
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public List<AlbumDTO> findAll() {
        return albumService.findAll();
    }

    @GetMapping("/{id}")
    public AlbumDTO findById( @PathVariable Long id ) {
        return albumService.findById(id);
    }

    @PostMapping
    public Album save(@RequestBody Album a) {
        return albumService.save(a);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable Long id, @RequestBody Album a) {
        return albumService.update(a, id);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return albumService.deleteById(id);
    }

}
