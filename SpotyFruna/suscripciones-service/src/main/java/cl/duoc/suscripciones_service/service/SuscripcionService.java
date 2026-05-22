package cl.duoc.suscripciones_service.service;

import cl.duoc.suscripciones_service.dto.UsuarioDTO;
import cl.duoc.suscripciones_service.exception.BadRequestException;
import cl.duoc.suscripciones_service.exception.FeignServiceException;
import cl.duoc.suscripciones_service.exception.ResourceNotFoundException;
import cl.duoc.suscripciones_service.model.Suscripcion;
import cl.duoc.suscripciones_service.repository.PlanRepository;
import cl.duoc.suscripciones_service.repository.SuscripcionRepository;
import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.clients.UsuarioClient;
import cl.duoc.suscripciones_service.mapper.SuscripcionMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SuscripcionService {

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private SuscripcionMapper mapper;

    public List<SuscripcionDTO> findAll() {
        List<SuscripcionDTO> suscripcionesDTO = new ArrayList<>();

        for(Suscripcion suscripcion : suscripcionRepository.findAll()) {
            UsuarioDTO usuarioDTO;

            try {
                usuarioDTO = usuarioClient.findById(suscripcion.getIdUsuario());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Usuario no encontrado");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con usuarios-service");
            }

            SuscripcionDTO suscripcionDTO = mapper.toDTO(suscripcion, usuarioDTO);
            suscripcionesDTO.add(suscripcionDTO);
        }

        return suscripcionesDTO;
    }

    public SuscripcionDTO findById(Long idSuscripcion) {
        Suscripcion suscripcion =  suscripcionRepository.findById(idSuscripcion).orElseThrow(() -> new ResourceNotFoundException("Suscripción no encontrada"));

        UsuarioDTO usuarioDTO;

        try {
            usuarioDTO = usuarioClient.findById(suscripcion.getIdUsuario());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con usuarios-service");
        }

        return mapper.toDTO(suscripcion,  usuarioDTO);
    }

    public List<SuscripcionDTO> findAllBetweenDates(LocalDate fechaMin, LocalDate fechaMax) {
        List<SuscripcionDTO> suscripcionesDTO = new ArrayList<>();

        if (fechaMin == null || fechaMax == null) {
            throw new BadRequestException("Fechas no pueden ser nulas");
        } else if (fechaMin.isAfter(fechaMax)) {
            throw new BadRequestException("Fecha mínima no puede ser mayor a fecha máxima");
        } else if (fechaMin.getYear() > LocalDate.now().getYear() || fechaMax.getYear() > LocalDate.now().getYear()) {
            throw new BadRequestException("El año de las fechas no puede ser mayor a " + LocalDate.now().getYear());
        }

        for (Suscripcion suscripcion : suscripcionRepository.findAll()) {
            LocalDate fechaInicio = suscripcion.getFechaInicio();

            if (
                (fechaInicio.isAfter(fechaMin)
                || fechaInicio.isEqual(fechaMin))
                &&
                (fechaInicio.isBefore(fechaMax)
                || fechaInicio.isEqual(fechaMax))
            ) {
                UsuarioDTO usuarioDTO;

                try {
                    usuarioDTO = usuarioClient.findById(suscripcion.getIdUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                SuscripcionDTO suscripcionDTO = mapper.toDTO(suscripcion, usuarioDTO);
                suscripcionesDTO.add(suscripcionDTO);
            }
        }

        return suscripcionesDTO;
    }

    public List<SuscripcionDTO> findAllByActivado(Boolean activado) {
        List<SuscripcionDTO> suscripcionesDTO = new ArrayList<>();

        if (activado == null) {
            throw new BadRequestException("Activado no puede ser nulo");
        }

        for (Suscripcion suscripcion : suscripcionRepository.findAll()) {
            if (Objects.equals(suscripcion.getActivado(), activado)) {
                UsuarioDTO usuarioDTO;

                try {
                    usuarioDTO = usuarioClient.findById(suscripcion.getIdUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                SuscripcionDTO suscripcionDTO = mapper.toDTO(suscripcion, usuarioDTO);
                suscripcionesDTO.add(suscripcionDTO);
            }
        }

        return suscripcionesDTO;
    }

    public List<SuscripcionDTO> findAllByPlan(Long idPlan) {
        List<SuscripcionDTO> suscripcionesDTO = new ArrayList<>();

        if (idPlan == null || idPlan < 1) {
            throw new BadRequestException("idPlan no puede ser menor a 1 ni nulo");
        }

        if (!planRepository.existsById(idPlan)) {
            throw new ResourceNotFoundException("Plan no encontrado");
        }

        for (Suscripcion suscripcion : suscripcionRepository.findAll()) {
            if (Objects.equals(suscripcion.getPlan().getId(), idPlan)) {
                UsuarioDTO usuarioDTO;

                try {
                    usuarioDTO = usuarioClient.findById(suscripcion.getIdUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                SuscripcionDTO suscripcionDTO = mapper.toDTO(suscripcion, usuarioDTO);
                suscripcionesDTO.add(suscripcionDTO);
            }
        }

        return suscripcionesDTO;
    }

    public Suscripcion save(Suscripcion suscripcion) {
        usuarioClient.findById(suscripcion.getIdUsuario());
        return suscripcionRepository.save(suscripcion);
    }

    public Suscripcion update(Long idSuscripcion, Suscripcion suscripcionNueva) {
        usuarioClient.findById(suscripcionNueva.getIdUsuario());

        Suscripcion suscripcion = suscripcionRepository.findById(idSuscripcion).orElseThrow(() -> new ResourceNotFoundException("Suscripción no encontrada"));

        suscripcion.setFechaInicio(suscripcionNueva.getFechaInicio());
        suscripcion.setFechaTermino(suscripcionNueva.getFechaTermino());
        suscripcion.setActivado(suscripcionNueva.getActivado());
        suscripcion.setPlan(suscripcionNueva.getPlan());
        suscripcion.setIdUsuario(suscripcionNueva.getIdUsuario());

        return suscripcionRepository.save(suscripcion);
    }

    public void deleteById(Long idSuscripcion) {
        if(!suscripcionRepository.existsById(idSuscripcion)) {
            throw new ResourceNotFoundException("Suscripción no encontrada");
        }
        suscripcionRepository.deleteById(idSuscripcion);
    }

}
