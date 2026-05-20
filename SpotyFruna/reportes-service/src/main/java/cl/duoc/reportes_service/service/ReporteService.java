package cl.duoc.reportes_service.service;

import cl.duoc.reportes_service.exception.BadRequestException;
import cl.duoc.reportes_service.exception.FeignServiceException;
import cl.duoc.reportes_service.exception.ResourceNotFoundException;
import cl.duoc.reportes_service.model.Reporte;
import cl.duoc.reportes_service.repository.EstadoRepository;
import cl.duoc.reportes_service.repository.ReporteRepository;
import cl.duoc.reportes_service.dto.ReporteDTO;
import cl.duoc.reportes_service.dto.UsuarioDTO;
import cl.duoc.reportes_service.clients.UsuarioClient;
import cl.duoc.reportes_service.mapper.ReporteMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private ReporteMapper mapper;



    public List<ReporteDTO> findAll() {
        List<ReporteDTO> reportesDTO = new ArrayList<>();

        for (Reporte reporte : reporteRepository.findAll()) {
            UsuarioDTO administradorDTO;
            UsuarioDTO usuarioDTO;

            try {
                administradorDTO = usuarioClient.findById(reporte.getAdministrador());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Administrador no encontrado");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con usuarios-service");
            }

            try {
                usuarioDTO = usuarioClient.findById(reporte.getUsuario());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Usuario no encontrado");
            } catch (FeignException e) {
                throw new FeignServiceException("Error al conectar con usuarios-service");
            }

            ReporteDTO reporteDTO = mapper.toDTO(reporte, administradorDTO, usuarioDTO);
            reportesDTO.add(reporteDTO);
        }

        return reportesDTO;
    }

    public List<ReporteDTO> findAllByAdmin(Long idAdministrador) {
        List<ReporteDTO> reportesDTO = new ArrayList<>();

        if (idAdministrador == null || idAdministrador < 1) {
            throw new BadRequestException("idAdministrador no puede ser menor a 1 ni nulo");
        }

        for (Reporte reporte : reporteRepository.findAll()) {
            if (Objects.equals(reporte.getAdministrador(), idAdministrador)) {
                UsuarioDTO administradorDTO;
                UsuarioDTO usuarioDTO;

                try {
                    administradorDTO = usuarioClient.findById(reporte.getAdministrador());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Administrador no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                try {
                    usuarioDTO = usuarioClient.findById(reporte.getUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                ReporteDTO reporteDTO = mapper.toDTO(reporte, administradorDTO, usuarioDTO);
                reportesDTO.add(reporteDTO);
            }
        }

        return reportesDTO;
    }

    public List<ReporteDTO> findAllByUsuario(Long idUsuario) {
        List<ReporteDTO> reportesDTO = new ArrayList<>();

        if (idUsuario == null || idUsuario < 1) {
            throw new BadRequestException("idUsuario no puede ser menor a 1 ni nulo");
        }

        for (Reporte reporte : reporteRepository.findAll()) {
            if (Objects.equals(reporte.getUsuario(), idUsuario)) {
                UsuarioDTO administradorDTO;
                UsuarioDTO usuarioDTO;

                try {
                    administradorDTO = usuarioClient.findById(reporte.getAdministrador());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Administrador no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                try {
                    usuarioDTO = usuarioClient.findById(reporte.getUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                ReporteDTO reporteDTO = mapper.toDTO(reporte, administradorDTO, usuarioDTO);
                reportesDTO.add(reporteDTO);
            }
        }

        return reportesDTO;
    }

    public List<ReporteDTO> findAllByEstado(Long idEstado) {
        List<ReporteDTO> reportesDTO = new ArrayList<>();

        if (idEstado == null || idEstado < 1) {
            throw new BadRequestException("idEstado no puede ser menor a 1 ni nulo");
        }
        if (!estadoRepository.existsById(idEstado)) {
            throw new ResourceNotFoundException("Estado no encontrado");
        }

        for (Reporte reporte : reporteRepository.findAll()) {
            if (Objects.equals(reporte.getEstado().getId(), idEstado)) {
                UsuarioDTO administradorDTO;
                UsuarioDTO usuarioDTO;

                try {
                    administradorDTO = usuarioClient.findById(reporte.getAdministrador());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Administrador no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                try {
                    usuarioDTO = usuarioClient.findById(reporte.getUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                ReporteDTO reporteDTO = mapper.toDTO(reporte, administradorDTO, usuarioDTO);
                reportesDTO.add(reporteDTO);
            }
        }

        return reportesDTO;
    }

    public List<ReporteDTO> findAllBetweenDates(LocalDate fechaMin, LocalDate fechaMax) {
        List<ReporteDTO> reportesDTO = new ArrayList<>();

        if (fechaMin == null || fechaMax == null) {
            throw new BadRequestException("Fechas no pueden ser nulas");
        } else if (fechaMin.isAfter(fechaMax)) {
            throw new BadRequestException("Fecha mínima no puede ser mayor a fecha máxima");
        } else if (fechaMin.getYear() > LocalDate.now().getYear() || fechaMax.getYear() > LocalDate.now().getYear()) {
            throw new BadRequestException("El año de las fechas no puede ser mayor a " + LocalDate.now().getYear());
        }

        for (Reporte reporte : reporteRepository.findAll()) {
            LocalDate fechaEnviado = reporte.getFechaEnviado();

            if (
                (fechaEnviado.isAfter(fechaMin)
                || fechaEnviado.isEqual(fechaMin))
                &&
                (fechaEnviado.isBefore(fechaMax)
                || fechaEnviado.isEqual(fechaMax))
            ) {
                UsuarioDTO administradorDTO;
                UsuarioDTO usuarioDTO;

                try {
                    administradorDTO = usuarioClient.findById(reporte.getAdministrador());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Administrador no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                try {
                    usuarioDTO = usuarioClient.findById(reporte.getUsuario());
                } catch (FeignException.NotFound e) {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                } catch (FeignException e) {
                    throw new FeignServiceException("Error al conectar con usuarios-service");
                }

                ReporteDTO reporteDTO = mapper.toDTO(reporte, administradorDTO, usuarioDTO);
                reportesDTO.add(reporteDTO);
            }
        }

        return reportesDTO;
    }

    public ReporteDTO findById(Long idReporte) {
        Reporte reporte = reporteRepository.findById(idReporte).orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado"));

        UsuarioDTO administradorDTO;
        UsuarioDTO usuarioDTO;

        try {
            administradorDTO = usuarioClient.findById(reporte.getAdministrador());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Administrador no encontrado");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con usuarios-service");
        }

        try {
            usuarioDTO = usuarioClient.findById(reporte.getUsuario());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con usuarios-service");
        }

        return mapper.toDTO(reporte, administradorDTO, usuarioDTO);
    }

    public Reporte save(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public Reporte update(Long idReporte, Reporte reporteNuevo) {
        Reporte reporte = reporteRepository.findById(idReporte).orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado"));

        reporte.setFechaResuelto(reporteNuevo.getFechaResuelto());
        reporte.setEstado(reporteNuevo.getEstado());

        return reporteRepository.save( reporte );
    }

    public void delete(Long idReporte) {
        if(!reporteRepository.existsById(idReporte)) {
           throw new ResourceNotFoundException("Reporte no encontrado");
        }
        reporteRepository.deleteById(idReporte);
    }

}
