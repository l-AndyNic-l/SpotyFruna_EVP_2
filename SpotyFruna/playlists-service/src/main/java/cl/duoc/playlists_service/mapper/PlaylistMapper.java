package cl.duoc.playlists_service.mapper;

import cl.duoc.playlists_service.model.Playlist;
import cl.duoc.playlists_service.dto.PlaylistDTO;
import cl.duoc.playlists_service.dto.CancionDTO;
import cl.duoc.playlists_service.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PlaylistMapper {

    public PlaylistDTO toDTO(Playlist p, List<CancionDTO> canciones, UsuarioDTO u) {
        if(p == null) {
            return null;
        }

        PlaylistDTO dto = new PlaylistDTO();

        dto.setId( p.getId() );
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dto.setFecha_creacion(p.getFechaCreacion().format(dtf));

        dto.setPrivacidad(p.getPrivacidad().getNombre());
        dto.setUsuario(u.getEmail());
        dto.setCanciones(canciones);

        return dto;
    }

}
