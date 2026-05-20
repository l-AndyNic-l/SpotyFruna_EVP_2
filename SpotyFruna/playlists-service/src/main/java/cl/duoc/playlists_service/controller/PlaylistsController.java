package cl.duoc.playlists_service.controller;

import cl.duoc.playlists_service.dto.PlaylistDTO;
import cl.duoc.playlists_service.model.Playlist;
import cl.duoc.playlists_service.service.PlaylistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/playlists" )
public class PlaylistsController {

    @Autowired
    private PlaylistService playlistService;



    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> findAll() {
        List<PlaylistDTO> playlistsDTO = playlistService.findAll();

        if (playlistsDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playlistsDTO);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<PlaylistDTO>> findAllByUsuario(@RequestParam(name = "id-usuario") Long idUsuario) {
        List<PlaylistDTO> playlistsDTO = playlistService.findAllByUsuario(idUsuario);

        if (playlistsDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playlistsDTO);
    }

    @GetMapping("/privacidad")
    public ResponseEntity<List<PlaylistDTO>> findAllByPrivacidad(@RequestParam(name = "id-privacidad") Long idPrivacidad) {
        List<PlaylistDTO> playlistsDTO = playlistService.findAllByPrivacidad(idPrivacidad);

        if (playlistsDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playlistsDTO);
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<PlaylistDTO>> findAllBetweenDates(@RequestParam(name = "fecha-min") LocalDate fechaMin, @RequestParam(name = "fecha-max") LocalDate fechaMax) {
        List<PlaylistDTO> playlistsDTO = playlistService.findAllBetweenDates(fechaMin, fechaMax);

        if (playlistsDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playlistsDTO);
    }

    @GetMapping( "/{idPlaylist}" )
    public ResponseEntity<PlaylistDTO> findById(@PathVariable Long idPlaylist) {
        PlaylistDTO playlist = playlistService.findById(idPlaylist);

        return ResponseEntity.ok(playlist);
    }

    @PostMapping
    public ResponseEntity<Playlist> save(@Valid @RequestBody Playlist playlist) {
        Playlist playlistInstanciada = playlistService.save(playlist);
        return new ResponseEntity<>(playlistInstanciada, HttpStatus.CREATED);
    }

    @PutMapping( "/{idPlaylist}" )
    public ResponseEntity<Playlist> update(@PathVariable Long idPlaylist, @Valid @RequestBody Playlist playlist) {
        Playlist playlistInstanciada = playlistService.update(idPlaylist, playlist);
        return ResponseEntity.ok(playlistInstanciada);
    }

    @DeleteMapping("/{idPlaylist}")
    public ResponseEntity<Void> delete(@PathVariable Long idPlaylist) {
        playlistService.deleteById(idPlaylist);
        return ResponseEntity.noContent().build();
    }


}
