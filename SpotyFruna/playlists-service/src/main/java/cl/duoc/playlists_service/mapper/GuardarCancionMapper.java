package cl.duoc.playlists_service.mapper;

import cl.duoc.playlists_service.dto.CancionDTO;
import cl.duoc.playlists_service.dto.GuardarCancionDTO;
import cl.duoc.playlists_service.dto.UsuarioDTO;
import cl.duoc.playlists_service.model.GuardarCancion;
import cl.duoc.playlists_service.model.Playlist;
import org.springframework.stereotype.Component;

@Component
public class GuardarCancionMapper {

    public GuardarCancionDTO toDTO(GuardarCancion g, Playlist p, CancionDTO c, UsuarioDTO u) {
        if(p == null) {
            return null;
        }

        GuardarCancionDTO dto = new GuardarCancionDTO();

        dto.setId( g.getId() );
        dto.setUsuario(u.getEmail());
        dto.setPlaylist(p.getNombre());
        dto.setCancion(c);

        return dto;
    }


}
