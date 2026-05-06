package cl.duoc.playlists_service.controller;

import cl.duoc.playlists_service.dto.PlaylistDTO;
import cl.duoc.playlists_service.model.Playlist;
import cl.duoc.playlists_service.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/listas" )
public class PlaylistsController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public List<PlaylistDTO> findAll() {
        return playlistService.findAll();
    }

    @GetMapping( "/{id}" )
    public PlaylistDTO findById(@PathVariable Long id) {
        return playlistService.findById(id);
    }

    @PostMapping
    public Playlist save(@RequestBody Playlist c) {
        return playlistService.save(c);
    }

    @PutMapping( "/{id}" )
    public int update(@PathVariable Long id, @RequestBody Playlist p) {
        return playlistService.update(p, id);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return playlistService.deleteById(id);
    }


}
