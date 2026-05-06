package cl.duoc.playlists_service.service;

import cl.duoc.playlists_service.dto.PlaylistDTO;
import cl.duoc.playlists_service.mapper.PlaylistMapper;
import cl.duoc.playlists_service.model.Playlist;
import cl.duoc.playlists_service.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistMapper mapper;

    public List<PlaylistDTO> findAll() {
        List<PlaylistDTO> listado = new ArrayList<>();

        for(Playlist c : playlistRepository.findAll()) {
            PlaylistDTO c_dto = mapper.toDTO(c);
            listado.add(c_dto);
        }

        return listado;
    }

    public PlaylistDTO findById(Long id) {
        Playlist p =  playlistRepository.findById(id).orElse(null);
        return mapper.toDTO(p);
    }

    public Playlist save(Playlist p) {
        return playlistRepository.save(p);
    }

    public int update(Playlist p, Long id) {
        return playlistRepository.update(p, id);
    }

    public Boolean deleteById(Long id) {
        if(playlistRepository.existsById(id)) {
            playlistRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
