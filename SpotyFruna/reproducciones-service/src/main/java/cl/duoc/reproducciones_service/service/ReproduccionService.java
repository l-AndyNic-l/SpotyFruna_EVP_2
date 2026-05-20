package cl.duoc.reproducciones_service.service;

import cl.duoc.reproducciones_service.exception.BadRequestException;
import cl.duoc.reproducciones_service.exception.FeignServiceException;
import cl.duoc.reproducciones_service.exception.ResourceNotFoundException;
import cl.duoc.reproducciones_service.model.Reproduccion;
import cl.duoc.reproducciones_service.repository.DispositivoRepository;
import cl.duoc.reproducciones_service.repository.ReproduccionRepository;
import cl.duoc.reproducciones_service.dto.ReproduccionDTO;
import cl.duoc.reproducciones_service.dto.CancionDTO;
import cl.duoc.reproducciones_service.dto.UsuarioDTO;
import cl.duoc.reproducciones_service.clients.CancionClient;
import cl.duoc.reproducciones_service.clients.UsuarioClient;
import cl.duoc.reproducciones_service.mapper.ReproduccionMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReproduccionService {

    @Autowired
    private ReproduccionRepository reproduccionRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private UsuarioClient usuarios;

    @Autowired
    private CancionClient canciones;

    @Autowired
    private ReproduccionMapper mapper;



    public List<ReproduccionDTO> findAll() {
        List<ReproduccionDTO> reproducciones = new ArrayList<>();

        for(Reproduccion reproduccion : reproduccionRepository.findAll()) {
            CancionDTO cancionDTO;
            UsuarioDTO usuarioDTO;

            try {
                cancionDTO = canciones.findById(reproduccion.getCancion());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Canción no encontrada");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con canciones-service");
            }

            try {
                usuarioDTO = usuarios.findById(reproduccion.getUsuario());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Usuario no encontrado");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con usuarios-service");
            }

            ReproduccionDTO reproduccionDTO = mapper.toDTO(reproduccion, cancionDTO, usuarioDTO);
            reproducciones.add(reproduccionDTO);
        }

        return reproducciones;
    }

    public ReproduccionDTO findById(Long idReproduccion) {
        if (idReproduccion == null || idReproduccion < 1) {
            throw new BadRequestException("idReproduccion no puede ser menor a 1 ni nulo");
        }

        Reproduccion reproduccion = reproduccionRepository.findById(idReproduccion).orElseThrow(() -> new ResourceNotFoundException("Reproducción no encontrada"));

        CancionDTO cancionDTO;
        UsuarioDTO usuarioDTO;

        try {
            cancionDTO = canciones.findById(reproduccion.getCancion());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Canción no encontrada");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con canciones-service");
        }

        try {
            usuarioDTO = usuarios.findById(reproduccion.getUsuario());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con usuarios-service");
        }

        return mapper.toDTO(reproduccion, cancionDTO, usuarioDTO);
    }

    public List<ReproduccionDTO> findAllByDispositivo(Long idDispositivo) {
        List<ReproduccionDTO> reproduccionesDTO = new ArrayList<>();

        if (idDispositivo == null || idDispositivo < 1) {
            throw new BadRequestException("idDispositivo no puede ser menor a 1 ni nulo");
        }

        if (!dispositivoRepository.existsById(idDispositivo)) {
            throw new ResourceNotFoundException("Dispositivo no encontrado");
        }

        for (Reproduccion reproduccion : reproduccionRepository.findAll()) {
            if (Objects.equals(reproduccion.getDispositivo().getId(), idDispositivo)) {
                CancionDTO cancionDTO;
                UsuarioDTO usuarioDTO;

                try {
                    cancionDTO = canciones.findById(reproduccion.getCancion());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Canción no encontrada");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con canciones-service");
                }

                try {
                    usuarioDTO = usuarios.findById(reproduccion.getUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                ReproduccionDTO reproduccionDTO = mapper.toDTO(reproduccion, cancionDTO, usuarioDTO);
                reproduccionesDTO.add(reproduccionDTO);
            }
        }

        return reproduccionesDTO;
    }

    public List<ReproduccionDTO> findAllByCancion(Long idCancion) {
        if (idCancion == null || idCancion < 1) {
            throw new BadRequestException("idCancion no puede ser menor a 1 ni nulo");
        }

        List<ReproduccionDTO> reproduccionesDTO = new ArrayList<>();

        for (Reproduccion reproduccion : reproduccionRepository.findAll()) {
            if (Objects.equals(reproduccion.getCancion(), idCancion)) {
                CancionDTO cancionDTO;
                UsuarioDTO usuarioDTO;

                try {
                    cancionDTO = canciones.findById(reproduccion.getCancion());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Canción no encontrada");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con canciones-service");
                }

                try {
                    usuarioDTO = usuarios.findById(reproduccion.getUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                ReproduccionDTO reproduccionDTO = mapper.toDTO(reproduccion, cancionDTO, usuarioDTO);
                reproduccionesDTO.add(reproduccionDTO);
            }
        }

        return reproduccionesDTO;
    }

    public List<ReproduccionDTO> findAllByUsuario(Long idUsuario) {
        if (idUsuario == null || idUsuario < 1) {
            throw new BadRequestException("idUsuario no puede ser menor a 1 ni nulo");
        }

        List<ReproduccionDTO> reproduccionesDTO = new ArrayList<>();

        for (Reproduccion reproduccion : reproduccionRepository.findAll()) {
            if (Objects.equals(reproduccion.getUsuario(), idUsuario)) {
                CancionDTO cancionDTO;
                UsuarioDTO usuarioDTO;

                try {
                    cancionDTO = canciones.findById(reproduccion.getCancion());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Canción no encontrada");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con canciones-service");
                }

                try {
                    usuarioDTO = usuarios.findById(reproduccion.getUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                ReproduccionDTO reproduccionDTO = mapper.toDTO(reproduccion, cancionDTO, usuarioDTO);
                reproduccionesDTO.add(reproduccionDTO);
            }
        }

        return reproduccionesDTO;
    }

    public List<ReproduccionDTO> findAllBetweenDates(LocalDate fechaMin, LocalDate fechaMax) {
        List<ReproduccionDTO> reproduccionesDTO = new ArrayList<>();

        if (fechaMin == null || fechaMax == null) {
            throw new BadRequestException("Fechas no pueden ser nulas");
        } else if (fechaMin.isAfter(fechaMax)) {
            throw new BadRequestException("Fecha mínima no puede ser mayor a fecha máxima");
        } else if (fechaMin.getYear() > LocalDate.now().getYear() || fechaMax.getYear() > LocalDate.now().getYear()) {
            throw new BadRequestException("El año de las fechas no puede ser mayor a " + LocalDate.now().getYear());
        }

        for (Reproduccion reproduccion : reproduccionRepository.findAll()) {
            LocalDate fechaReproduccion = reproduccion.getFechaReproduccion();
            if (
                (fechaReproduccion.isAfter(fechaMin)
                || fechaReproduccion.isEqual(fechaMin))
                &&
                (fechaReproduccion.isBefore(fechaMax)
                || fechaReproduccion.isEqual(fechaMax))
            ) {
                CancionDTO cancionDTO;
                UsuarioDTO usuarioDTO;

                try {
                    cancionDTO = canciones.findById(reproduccion.getCancion());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Canción no encontrada");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con canciones-service");
                }

                try {
                    usuarioDTO = usuarios.findById(reproduccion.getUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                ReproduccionDTO reproduccionDTO = mapper.toDTO(reproduccion, cancionDTO, usuarioDTO);
                reproduccionesDTO.add(reproduccionDTO);
            }
        }

        return reproduccionesDTO;
    }

    public Reproduccion save(Reproduccion reproduccion) {
        return reproduccionRepository.save(reproduccion);
    }

    public Reproduccion update(Long idReproduccion, Reproduccion reproduccionNueva) {
        Reproduccion reproduccion = reproduccionRepository.findById(idReproduccion).orElseThrow(() -> new ResourceNotFoundException("Reproducción no encontrada"));

        reproduccion.setSegundosEscuchados(reproduccionNueva.getSegundosEscuchados());

        return reproduccionRepository.save(reproduccion);
    }

    public void delete( Long idReproduccion ) {
        if(!reproduccionRepository.existsById(idReproduccion)) {
            throw new ResourceNotFoundException("Reproducción no encontrada");
        }
        reproduccionRepository.deleteById(idReproduccion);
    }

}
