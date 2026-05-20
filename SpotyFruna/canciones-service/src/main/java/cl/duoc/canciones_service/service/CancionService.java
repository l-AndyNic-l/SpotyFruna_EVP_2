package cl.duoc.canciones_service.service;

import cl.duoc.canciones_service.dto.AlbumCancionDTO;
import cl.duoc.canciones_service.dto.AlbumDTO;
import cl.duoc.canciones_service.exception.BadRequestException;
import cl.duoc.canciones_service.exception.ConflictException;
import cl.duoc.canciones_service.exception.FeignServiceException;
import cl.duoc.canciones_service.exception.ResourceNotFoundException;
import cl.duoc.canciones_service.mapper.AlbumCancionMapper;
import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.repository.CancionRepository;
import cl.duoc.canciones_service.dto.CancionDTO;
import cl.duoc.canciones_service.clients.AlbumClient;
import cl.duoc.canciones_service.mapper.CancionMapper;
import cl.duoc.canciones_service.repository.GeneroRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private AlbumClient albumClient;

    @Autowired
    private AlbumCancionMapper albumMapper;

    @Autowired
    private CancionMapper mapper;

    public List<CancionDTO> findAll() {
        List<CancionDTO> cancionesDTO = new ArrayList<>();

        for(Cancion cancion : cancionRepository.findAll()) {
            AlbumDTO album;
            try {
                album = albumClient.findById(cancion.getIdAlbum());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Álbum no encontrado");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con album-service");
            }

            CancionDTO cancionDTO = mapper.toDTO(cancion, album);
            cancionesDTO.add(cancionDTO);
        }

        return cancionesDTO;
    }

    public CancionDTO findById(Long idCancion) {
        Cancion cancion = cancionRepository.findById(idCancion).orElseThrow(() -> new ResourceNotFoundException("Canción no encontrada"));
        AlbumDTO album;

        try {
            album = albumClient.findById(cancion.getIdAlbum());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Álbum no encontrado");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con album-service");
        }
        return mapper.toDTO(cancion, album);
    }

    public List<AlbumCancionDTO> findAllByIdAlbum(Long idAlbum) {
        List<AlbumCancionDTO> cancionesDTO = new ArrayList<>();


        try {
            albumClient.findById(idAlbum);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Álbum no encontrado");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con album-service");
        }

        for(Cancion cancion : cancionRepository.findAllByIdAlbum(idAlbum)) {
            AlbumCancionDTO cancionDTO = albumMapper.toDTO(cancion);
            cancionesDTO.add(cancionDTO);
        }

        return cancionesDTO;
    }

    public List<CancionDTO> findAllByAutor(String autor) {
        List<CancionDTO> cancionesDTO = new ArrayList<>();

        if (!cancionRepository.existsByAutor(autor)) {
            throw new ResourceNotFoundException("Autor no encontrado");
        }

        for (Cancion cancion : cancionRepository.findAll()) {
            if (Objects.equals(cancion.getAutor(), autor)) {
                AlbumDTO album;
                try {
                    album = albumClient.findById(cancion.getIdAlbum());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Álbum no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con album-service");
                }
                CancionDTO cancionDTO = mapper.toDTO(cancion, album);
                cancionesDTO.add(cancionDTO);
            }
        }

        return cancionesDTO;
    }

    public List<CancionDTO> findAllByGenero(Long idGenero) {
        List<CancionDTO> cancionesDTO = new ArrayList<>();

        if (!generoRepository.existsById(idGenero)) {
            throw new ResourceNotFoundException("Género no encontrado");
        }

        for (Cancion cancion : cancionRepository.findAll()) {
            if (Objects.equals(cancion.getGenero().getId(), idGenero)) {
                AlbumDTO album;
                try {
                    album = albumClient.findById(cancion.getIdAlbum());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Álbum no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con album-service");
                }
                CancionDTO cancionDTO = mapper.toDTO(cancion, album);
                cancionesDTO.add(cancionDTO);
            }
        }

        return cancionesDTO;
    }

    public List<CancionDTO> findAllBetweenDates(LocalDate fechaMin, LocalDate fechaMax) {
        List<CancionDTO> cancionesDTO = new ArrayList<>();

        if (fechaMin == null || fechaMax == null) {
            throw new BadRequestException("Fechas no pueden ser nulas");
        } else if (fechaMin.isAfter(fechaMax)) {
            throw new BadRequestException("Fecha mínima no puede ser mayor a fecha máxima");
        } else if (fechaMin.getYear() > LocalDate.now().getYear() || fechaMax.getYear() > LocalDate.now().getYear()) {
            throw new BadRequestException("El año de las fechas no puede ser mayor a " + LocalDate.now().getYear());
        }

        for (Cancion cancion : cancionRepository.findAll()) {
            LocalDate fechaLanzamiento = cancion.getFechaLanzamiento();

            if (
                (fechaLanzamiento.isAfter(fechaMin)
                || fechaLanzamiento.isEqual(fechaMin))
                &&
                (fechaLanzamiento.isBefore(fechaMax)
                || fechaLanzamiento.isEqual(fechaMax))
            ) {

                AlbumDTO album;
                try {
                    album = albumClient.findById(cancion.getIdAlbum());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Álbum no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con album-service");
                }
                CancionDTO cancionDTO = mapper.toDTO(cancion, album);
                cancionesDTO.add(cancionDTO);
            }
        }

        return cancionesDTO;
    }

    public List<CancionDTO> findAllBetweenDuration(Long duracionMin, Long duracionMax) {
        List<CancionDTO> cancionesDTO = new ArrayList<>();

        if (duracionMin == null || duracionMax == null) {
            throw new BadRequestException("Duraciones no pueden ser nulas");
        }
        if (duracionMin < 0 || duracionMax < 0) {
            throw new BadRequestException("Duraciones mínima no puede ser menor a 0");
        }
        if (duracionMin > duracionMax) {
            throw new BadRequestException("Duración mínima no puede ser mayor a duración máxima");
        }

        for (Cancion cancion : cancionRepository.findAll()) {
            if (cancion.getDuracion() >= duracionMin && cancion.getDuracion() <= duracionMax) {
                AlbumDTO album;
                try {
                    album = albumClient.findById(cancion.getIdAlbum());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Álbum no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con album-service");
                }
                CancionDTO cancionDTO = mapper.toDTO(cancion, album);
                cancionesDTO.add(cancionDTO);
            }
        }

        return cancionesDTO;
    }

    public Cancion save(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    public Cancion update(Long idCancion, Cancion cancionNueva) {
        Cancion cancion = cancionRepository.findById(idCancion).orElseThrow(() -> new ResourceNotFoundException("Canción no encontrada"));

        cancion.setAutor(cancionNueva.getAutor());
        cancion.setTitulo(cancionNueva.getTitulo());
        cancion.setDuracion(cancionNueva.getDuracion());
        cancion.setFechaLanzamiento(cancionNueva.getFechaLanzamiento());
        cancion.setGenero(cancionNueva.getGenero());
        cancion.setIdAlbum(cancionNueva.getIdAlbum());

        return cancionRepository.save(cancion);
    }

    public void deleteById(Long idCancion) {
        if(!cancionRepository.existsById(idCancion)) {
            throw new ResourceNotFoundException("Canción no encontrada");
        }
        cancionRepository.deleteById(idCancion);
    }

}
