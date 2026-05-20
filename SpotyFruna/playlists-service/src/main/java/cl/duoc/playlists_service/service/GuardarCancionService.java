package cl.duoc.playlists_service.service;

import cl.duoc.playlists_service.clients.CancionClient;
import cl.duoc.playlists_service.clients.UsuarioClient;
import cl.duoc.playlists_service.dto.CancionDTO;
import cl.duoc.playlists_service.dto.UsuarioDTO;
import cl.duoc.playlists_service.dto.GuardarCancionDTO;
import cl.duoc.playlists_service.exception.ConflictException;
import cl.duoc.playlists_service.exception.FeignServiceException;
import cl.duoc.playlists_service.exception.ResourceNotFoundException;
import cl.duoc.playlists_service.mapper.GuardarCancionMapper;
import cl.duoc.playlists_service.model.GuardarCancion;
import cl.duoc.playlists_service.model.Playlist;
import cl.duoc.playlists_service.repository.GuardarCancionRepository;
import cl.duoc.playlists_service.repository.PlaylistRepository;
import feign.FeignException;
import org.bouncycastle.pqc.crypto.ExchangePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GuardarCancionService {

    @Autowired
    private GuardarCancionRepository guardarCancionRepository;

    @Autowired
    private CancionClient cancionClient;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private GuardarCancionMapper mapper;

    public List<GuardarCancionDTO> findAll() {
        List<GuardarCancionDTO> guardados = new ArrayList<>();

        for(GuardarCancion g : guardarCancionRepository.findAll()) {
            CancionDTO c;
            UsuarioDTO u;

            try {
                c = cancionClient.findById(g.getIdCancion());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Canción no encontrada");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con canciones-service");
            }

            Playlist p = playlistRepository.findById(g.getPlaylist().getId()).orElseThrow(() -> new ResourceNotFoundException("Playlist no encontrada"));

            try {
                u = usuarioClient.findById(p.getIdUsuario());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Usuario no encontrado");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con usuarios-service");
            }

            guardados.add(mapper.toDTO(g, p, c, u));
        }

        return guardados;
    }

    public GuardarCancionDTO findById(Long idGuardarCancion) {
        GuardarCancion g = guardarCancionRepository.findById(idGuardarCancion).orElseThrow(() -> new ResourceNotFoundException("Canción guardada no encontrada"));
        Playlist p = playlistRepository.findById(g.getPlaylist().getId()).orElseThrow(() -> new ResourceNotFoundException("Playlist no encontrada"));
        UsuarioDTO u;
        CancionDTO c;

        try {
            c = cancionClient.findById(g.getIdCancion());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Canción no encontrada");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con canciones-service");
        }

        try {
            u = usuarioClient.findById(p.getIdUsuario());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con usuarios-service");
        }

        return mapper.toDTO(g, p, c, u);
    }

    public GuardarCancion save(GuardarCancion guardarCancion) {
        return guardarCancionRepository.save(guardarCancion);
    }

    public GuardarCancion update(Long idGuardarCancion, GuardarCancion guardarCancionNueva) {
        GuardarCancion playlistCancion = guardarCancionRepository.findById(idGuardarCancion).orElseThrow(() -> new ResourceNotFoundException("Canción guardada no encontrada"));

        playlistCancion.setPlaylist(guardarCancionNueva.getPlaylist());
        playlistCancion.setIdCancion(guardarCancionNueva.getIdCancion());

        return guardarCancionRepository.save(playlistCancion);
    }

    public void deleteById(Long idGuardarCancion) {
        if (!guardarCancionRepository.existsById(idGuardarCancion)) {
            throw new ResourceNotFoundException("Cancion guardada no encontrada");
        }
        guardarCancionRepository.deleteById(idGuardarCancion);
    }

}
