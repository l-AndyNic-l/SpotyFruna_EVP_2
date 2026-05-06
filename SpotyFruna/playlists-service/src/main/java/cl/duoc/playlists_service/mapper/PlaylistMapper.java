package cl.duoc.playlists_service.mapper;


import cl.duoc.playlists_service.clients.GenerosFeign;
import cl.duoc.playlists_service.clients.CancionesFeign;
import cl.duoc.playlists_service.dto.PlaylistDTO;
import cl.duoc.playlists_service.dto.CancionDTO;
import cl.duoc.playlists_service.model.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaylistMapper {

    @Autowired
    private CancionesFeign canciones;
    private GenerosFeign generos;

    public PlaylistDTO toDTO(Playlist p) {
        if(p == null) {
            return null;
        }

        PlaylistDTO dto = new PlaylistDTO();
        List<CancionDTO> dtoCanciones = canciones.findAll();

        dto.setNombre_playlist(p.getNombre_playlist());
        dto.setDescripcion(p.getDescripcion());
        dto.setFecha_creacion(p.getFecha_creacion());
        dto.setPrivacidad(p.getPrivacidad());
        dto.setCanciones(dtoCanciones);

        return dto;
    }
}
