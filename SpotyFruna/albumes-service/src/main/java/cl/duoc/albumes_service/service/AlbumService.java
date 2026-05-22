package cl.duoc.albumes_service.service;

import cl.duoc.albumes_service.clients.UsuarioClient;
import cl.duoc.albumes_service.dto.UsuarioDTO;
import cl.duoc.albumes_service.exception.BadRequestException;
import cl.duoc.albumes_service.exception.ConflictException;
import cl.duoc.albumes_service.exception.FeignServiceException;
import cl.duoc.albumes_service.exception.ResourceNotFoundException;
import cl.duoc.albumes_service.model.Album;
import cl.duoc.albumes_service.repository.AlbumRepository;
import cl.duoc.albumes_service.dto.AlbumDTO;
import cl.duoc.albumes_service.dto.AlbumCancionDTO;
import cl.duoc.albumes_service.clients.CancionClient;
import cl.duoc.albumes_service.mapper.AlbumMapper;
import cl.duoc.albumes_service.repository.TipoAlbumRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private TipoAlbumRepository tipoAlbumRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private CancionClient cancionClient;

    @Autowired
    private AlbumMapper mapper;



    public List<AlbumDTO> findAll() {
        List<AlbumDTO> albumDTOS = new ArrayList<>();

        for (Album album : albumRepository.findAll()) {
            UsuarioDTO usuarioDTO;
            List<AlbumCancionDTO> canciones;

            try {
                usuarioDTO = usuarioClient.findById(album.getArtista());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Artista no encontrado/a");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con usuarios-service");
            }

            try {
                canciones = cancionClient.findAllByIdAlbum(album.getId());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Canción no encontrada");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con canciones-service");
            }

            AlbumDTO dto = mapper.toDTO(album, usuarioDTO, canciones);
            albumDTOS.add(dto);
        }

        return albumDTOS;
    }

    public AlbumDTO findById(Long idAlbum) {
        Album album = albumRepository.findById(idAlbum).orElseThrow(() -> new ResourceNotFoundException("Álbum no encontrado"));
        UsuarioDTO usuarioDTO;
        List<AlbumCancionDTO> canciones;

        try {
            usuarioDTO = usuarioClient.findById(album.getArtista());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Artista no encontrado/a");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con usuarios-service");
        }

        try {
            canciones = cancionClient.findAllByIdAlbum(idAlbum);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Canción no encontrada");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con canciones-service");
        }

        return mapper.toDTO(album, usuarioDTO,  canciones);
    }

    public List<AlbumDTO> findAllByTipoAlbum(Long idTipoAlbum) {
        List<AlbumDTO> albumesDTO = new ArrayList<>();

        if (idTipoAlbum == null || idTipoAlbum < 1) {
            throw new BadRequestException("idTipoAlbum debe ser un número mayor a 0");
        }
        if (!tipoAlbumRepository.existsById(idTipoAlbum)) {
            throw new ResourceNotFoundException("Tipo álbum no encontrado");
        }

        for (Album album : albumRepository.findAll()) {
            if (Objects.equals(album.getTipoAlbum().getId(), idTipoAlbum)) {
                UsuarioDTO usuarioDTO;
                List<AlbumCancionDTO> canciones;

                try {
                    usuarioDTO = usuarioClient.findById(album.getArtista());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Artista no encontrado/a");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                try {
                    canciones = cancionClient.findAllByIdAlbum(album.getId());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Canción no encontrada");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con canciones-service");
                }

                AlbumDTO dto = mapper.toDTO(album, usuarioDTO, canciones);
                albumesDTO.add(dto);
            }
        }

        return albumesDTO;
    }

    public List<AlbumDTO> findAllBetweenDates(LocalDate fechaMin, LocalDate fechaMax) {
        List<AlbumDTO> albumesDTO = new ArrayList<>();

        if (fechaMin == null || fechaMax == null) {
            throw new BadRequestException("Fechas no pueden ser nulas");
        } else if (fechaMin.isAfter(fechaMax)) {
            throw new BadRequestException("Fecha mínima no puede ser mayor a fecha máxima");
        } else if (fechaMin.getYear() > LocalDate.now().getYear() || fechaMax.getYear() > LocalDate.now().getYear()) {
            throw new BadRequestException("El año de las fechas no puede ser mayor a " + LocalDate.now().getYear());
        }

        for (Album album : albumRepository.findAll()) {
            LocalDate fechaLanzamiento = album.getFechaLanzamiento();

            if (
                (fechaLanzamiento.isAfter(fechaMin)
                || fechaLanzamiento.isEqual(fechaMin))
                &&
                (fechaLanzamiento.isBefore(fechaMax)
                || fechaLanzamiento.isEqual(fechaMax))
            ) {
                UsuarioDTO usuarioDTO;
                List<AlbumCancionDTO> canciones;

                try {
                    usuarioDTO = usuarioClient.findById(album.getArtista());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Artista no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                try {
                    canciones = cancionClient.findAllByIdAlbum(album.getId());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Canción no encontrada");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con canciones-service");
                }

                AlbumDTO dto = mapper.toDTO(album, usuarioDTO, canciones);
                albumesDTO.add(dto);
            }
        }

        return albumesDTO;
    }

    public List<AlbumDTO> findAllByArtist(Long idArtista) {
        List<AlbumDTO> albumesDTO = new ArrayList<>();

        if (idArtista == null || idArtista < 1) {
            throw new BadRequestException("idArtista debe ser un número mayor a 0");
        }
        try {
            usuarioClient.findById(idArtista);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Artista no encontrado/a");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con usuarios-service");
        }

        for (Album album: albumRepository.findAll()) {
            if (Objects.equals(album.getArtista(), idArtista)) {
                UsuarioDTO usuarioDTO;
                List<AlbumCancionDTO> canciones;

                try {
                    usuarioDTO = usuarioClient.findById(album.getArtista());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Artista no encontrado/a");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                try {
                    canciones = cancionClient.findAllByIdAlbum(album.getId());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Canción no encontrada");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con canciones-service");
                }

                AlbumDTO dto = mapper.toDTO(album, usuarioDTO, canciones);
                albumesDTO.add(dto);
            }
        }

        return albumesDTO;
    }

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public Album update(Long idAlbum, Album albumNuevo) {
        Album album = albumRepository.findById(idAlbum).orElseThrow(() -> new ResourceNotFoundException("Álbum no encontrado"));

        album.setNombre(albumNuevo.getNombre());
        album.setDescripcion(albumNuevo.getDescripcion());
        album.setFechaLanzamiento(albumNuevo.getFechaLanzamiento());
        album.setTipoAlbum(albumNuevo.getTipoAlbum());

        return albumRepository.save(album);
    }

    public void deleteById(Long idAlbum) {
        if(!albumRepository.existsById(idAlbum)) {
            throw new ResourceNotFoundException("Álbum no encontrado");
        }
        albumRepository.deleteById(idAlbum);
    }

}
