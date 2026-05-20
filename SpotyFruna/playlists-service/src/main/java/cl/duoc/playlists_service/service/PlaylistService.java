package cl.duoc.playlists_service.service;

import cl.duoc.playlists_service.exception.BadRequestException;
import cl.duoc.playlists_service.exception.ConflictException;
import cl.duoc.playlists_service.exception.FeignServiceException;
import cl.duoc.playlists_service.exception.ResourceNotFoundException;
import cl.duoc.playlists_service.model.Playlist;
import cl.duoc.playlists_service.model.GuardarCancion;
import cl.duoc.playlists_service.repository.PlaylistRepository;
import cl.duoc.playlists_service.repository.GuardarCancionRepository;
import cl.duoc.playlists_service.dto.PlaylistDTO;
import cl.duoc.playlists_service.dto.CancionDTO;
import cl.duoc.playlists_service.dto.UsuarioDTO;
import cl.duoc.playlists_service.clients.CancionClient;
import cl.duoc.playlists_service.clients.UsuarioClient;
import cl.duoc.playlists_service.mapper.PlaylistMapper;
import cl.duoc.playlists_service.repository.PrivacidadRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private GuardarCancionRepository guardarCancionRepository;

    @Autowired
    private PrivacidadRepository privacidadRepository;

    @Autowired
    private CancionClient cancionClient;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private PlaylistMapper mapper;



    public List<PlaylistDTO> findAll() {
        List<PlaylistDTO> playlistsDTO = new ArrayList<>();

        for(Playlist p : playlistRepository.findAll()) {
            List<CancionDTO> canciones = new ArrayList<>();
            UsuarioDTO usuarioDTO;

            try {
                usuarioDTO = usuarioClient.findById(p.getIdUsuario());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Usuario no encontrado");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con usuarios-service");
            }


            for(GuardarCancion guardarCancion : guardarCancionRepository.findAllByPlaylist(p)) {
                CancionDTO cancionDTO;

                try {
                    cancionDTO = cancionClient.findById(guardarCancion.getIdCancion());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Canción no encontrada");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con canciones-service");
                }

                canciones.add(cancionDTO);
            }

            PlaylistDTO p_dto = mapper.toDTO(p, canciones, usuarioDTO);
            playlistsDTO.add(p_dto);
        }

        return playlistsDTO;
    }

    public PlaylistDTO findById(Long id) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Playlist no encontrada"));
        List<CancionDTO> cancionesDTO = new ArrayList<>();
        UsuarioDTO usuarioDTO;

        try {
            usuarioDTO = usuarioClient.findById(playlist.getIdUsuario());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con usuarios-service");
        }


        for(GuardarCancion guardarCancion : guardarCancionRepository.findAllByPlaylist(playlist)) {
            CancionDTO cancionDTO;

            try {
                cancionDTO = cancionClient.findById(guardarCancion.getIdCancion());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Canción no encontrada");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con canciones-service");
            }
            cancionesDTO.add(cancionDTO);
        }

        return mapper.toDTO(playlist, cancionesDTO, usuarioDTO);
    }

    public List<PlaylistDTO> findAllByPrivacidad(Long idPrivacidad) {
        List<PlaylistDTO> playlistsDTO = new ArrayList<>();

        if (idPrivacidad == null || idPrivacidad < 1) {
            throw new BadRequestException("idPrivacidad no puede ser nulo ni menor de 1");
        }

        if (!privacidadRepository.existsById(idPrivacidad)) {
            throw new ResourceNotFoundException("Privacidad no encontrada");
        }

        for (Playlist playlist : playlistRepository.findAll()) {
            if (Objects.equals(playlist.getPrivacidad().getId(), idPrivacidad)) {
                List<CancionDTO> canciones = new ArrayList<>();
                UsuarioDTO usuarioDTO;

                try {
                    usuarioDTO = usuarioClient.findById(playlist.getIdUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con canciones-service");
                }

                for(GuardarCancion guardarCancion : guardarCancionRepository.findAllByPlaylist(playlist)) {
                    CancionDTO cancionDTO;

                    try {
                        cancionDTO = cancionClient.findById(guardarCancion.getIdCancion());
                    } catch (FeignException.NotFound e) {
                        throw new ResourceNotFoundException("Canción no encontrada");
                    } catch (FeignException e) {
                        throw new FeignServiceException("Error al conectar con canciones-service");
                    }

                    canciones.add(cancionDTO);
                }

                PlaylistDTO p_dto = mapper.toDTO(playlist, canciones, usuarioDTO);
                playlistsDTO.add(p_dto);
            }
        }

        return playlistsDTO;
    }

    public List<PlaylistDTO> findAllByUsuario(Long idUsuario) {
        List<PlaylistDTO> playlistsDTO = new ArrayList<>();

        if (idUsuario == null || idUsuario < 1) {
            throw new BadRequestException("idUsuario no puede ser nulo ni menor de 1");
        }

        try {
            usuarioClient.findById(idUsuario);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con usuarios-service");
        }

        for (Playlist playlist : playlistRepository.findAll()) {
            if (Objects.equals(playlist.getIdUsuario(), idUsuario)) {
                List<CancionDTO> canciones = new ArrayList<>();
                UsuarioDTO usuarioDTO;

                try {
                    usuarioDTO = usuarioClient.findById(playlist.getIdUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                for(GuardarCancion guardarCancion : guardarCancionRepository.findAllByPlaylist(playlist)) {
                    CancionDTO cancionDTO;

                    try {
                        cancionDTO = cancionClient.findById(guardarCancion.getIdCancion());
                    } catch (FeignException.NotFound e) {
                        throw new ResourceNotFoundException("Canción no encontrada");
                    } catch (FeignException e) {
                        throw new FeignServiceException("Error al conectar con canciones-service");
                    }

                    canciones.add(cancionDTO);
                }

                PlaylistDTO playlistDTO = mapper.toDTO(playlist, canciones, usuarioDTO);
                playlistsDTO.add(playlistDTO);
            }
        }

        return playlistsDTO;
    }

    public List<PlaylistDTO> findAllBetweenDates(LocalDate fechaMin, LocalDate fechaMax) {
        List<PlaylistDTO> playlistsDTO = new ArrayList<>();

        if (fechaMin == null || fechaMax == null) {
            throw new BadRequestException("Fechas no pueden ser nulas");
        } else if (fechaMin.isAfter(fechaMax)) {
            throw new BadRequestException("Fecha mínima no puede ser mayor a fecha máxima");
        } else if (fechaMin.getYear() > LocalDate.now().getYear() || fechaMax.getYear() > LocalDate.now().getYear()) {
            throw new BadRequestException("El año de las fechas no puede ser mayor a " + LocalDate.now().getYear());
        }

        for (Playlist playlist : playlistRepository.findAll()) {
            LocalDate fechaCreacion = playlist.getFechaCreacion();

            if (
                (fechaCreacion.isAfter(fechaMin)
                || fechaCreacion.isEqual(fechaMin))
                &&
                (fechaCreacion.isBefore(fechaMax)
                || fechaCreacion.isEqual(fechaMax))
            ) {
                List<CancionDTO> canciones = new ArrayList<>();
                UsuarioDTO usuarioDTO;
                try {
                    usuarioDTO = usuarioClient.findById(playlist.getIdUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }
                for(GuardarCancion guardarCancion : guardarCancionRepository.findAllByPlaylist(playlist)) {
                    CancionDTO cancionDTO;

                    try {
                        cancionDTO = cancionClient.findById(guardarCancion.getIdCancion());
                    } catch (FeignException.NotFound e) {
                        throw new ResourceNotFoundException("Canción no encontrada");
                    } catch (FeignException e) {
                        throw new FeignServiceException("Error al conectar con canciones-service");
                    }

                    canciones.add(cancionDTO);
                }

                PlaylistDTO playlistDTO = mapper.toDTO(playlist, canciones, usuarioDTO);
                playlistsDTO.add(playlistDTO);
            }
        }

        return playlistsDTO;
    }

    public Playlist save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Playlist update(Long idPlaylist, Playlist playlistNueva) {
        Playlist playlist = playlistRepository.findById(idPlaylist).orElseThrow(() -> new ResourceNotFoundException("Playlist no encontrada"));

        playlist.setNombre(playlistNueva.getNombre());
        playlist.setDescripcion(playlistNueva.getDescripcion());
        playlist.setPrivacidad(playlistNueva.getPrivacidad());

        return playlistRepository.save(playlist);
    }

    public void deleteById(Long idPlaylist) {
        if(!playlistRepository.existsById(idPlaylist)) {
            throw new ResourceNotFoundException("Playlist no encontrada");
        }
        playlistRepository.deleteById(idPlaylist);
    }

}
