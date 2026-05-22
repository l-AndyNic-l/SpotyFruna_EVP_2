package cl.duoc.auth_service.service;

import cl.duoc.auth_service.clients.UsuarioClient;
import cl.duoc.auth_service.dto.UsuarioDTO;
import cl.duoc.auth_service.exception.BadRequestException;
import cl.duoc.auth_service.exception.ConflictException;
import cl.duoc.auth_service.exception.FeignServiceException;
import cl.duoc.auth_service.exception.ResourceNotFoundException;
import cl.duoc.auth_service.model.Auth;
import cl.duoc.auth_service.repository.AuthRepository;
import cl.duoc.auth_service.dto.AuthDTO;
import cl.duoc.auth_service.mapper.AuthMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private AuthMapper mapper;



    public List<AuthDTO>  findAll() {
        List<AuthDTO> authsDTO = new ArrayList<>();

        for (Auth auth : authRepository.findAll()) {
            AuthDTO authDTO = mapper.toDTO(auth);
            authsDTO.add(authDTO);
        }

        return authsDTO;
    }

    public List<AuthDTO> findAllByUsuario(Long idUsuario) {
        List<AuthDTO> authsDTO = new ArrayList<>();

        if (idUsuario == null || idUsuario < 1) {
            throw new BadRequestException("idUsuario debe ser un número mayor a 0");
        }

        try {
            usuarioClient.findById(idUsuario);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        } catch (FeignException e) {
            throw new FeignServiceException("Error al conectar con usuarios-service");
        }

        for (Auth auth : authRepository.findAllByUsuario(idUsuario)) {
            AuthDTO authDTO = mapper.toDTO(auth);
            authsDTO.add(authDTO);
        }

        return authsDTO;
    }

    public List<AuthDTO> findAllByAnio(int anio) {
        List<AuthDTO> authsDTO = new ArrayList<>();

        if (anio > LocalDate.now().getYear()) {
            throw new BadRequestException("Año no puede ser mayor a " + LocalDate.now().getYear());
        } else if (anio < 1860) {
            throw new BadRequestException("Año no puede ser menor a 1860");
        }

        for(Auth auth : authRepository.findAllByAnio(anio)) {
            AuthDTO authDTO = mapper.toDTO(auth);
            authsDTO.add(authDTO);
        }

        return authsDTO;
    }

    public List<AuthDTO> findAllByMes(int mes, int anio) {
        List<AuthDTO> authsDTO = new ArrayList<>();

        if (anio < 1860 || anio > LocalDate.now().getYear()) {
            throw new BadRequestException("El año debe estar entre 1860 y " + LocalDate.now().getYear());
        }

        if (mes < 1 || mes > 12) {
            throw new BadRequestException("El mes debe estar entre 1 y 12");
        }

        for(Auth auth : authRepository.findAllByMes(mes, anio)) {
            AuthDTO authDTO = mapper.toDTO(auth);
            authsDTO.add(authDTO);
        }

        return authsDTO;
    }

    public List<AuthDTO> findAllByDia(int dia, int mes, int anio) {
        List<AuthDTO> authsDTO = new ArrayList<>();

        if (anio < 1860 || anio > LocalDate.now().getYear()) {
            throw new BadRequestException("El año debe estar entre 1860 y " + LocalDate.now().getYear());
        }

        if (mes < 1 || mes > 12) {
            throw new BadRequestException("El mes debe estar entre 1 y 12");
        }

        if (dia < 1 || dia > 31) {
            throw new BadRequestException("El dia debe estar entre 1 y 31");
        }

        for(Auth auth : authRepository.findAllByDia(dia, mes, anio)) {
            AuthDTO authDTO = mapper.toDTO(auth);
            authsDTO.add(authDTO);
        }

        return authsDTO;
    }

    public List<AuthDTO> findAllByEntreFechas(String fecha_ini, String fecha_ter) {
        List<AuthDTO> authsDTO = new ArrayList<>();
        LocalDate fechaMin;
        LocalDate fechaMax;

        try {
            fechaMin = LocalDate.parse(fecha_ini);
            fechaMax = LocalDate.parse(fecha_ter);
        } catch (Exception e) {
            throw new BadRequestException("Formato de fecha inválido. Use yyyy-MM-dd");
        }

        if (fechaMin.isAfter(fechaMax)) {
            throw new BadRequestException("Fecha mínima no puede ser mayor a fecha máxima");
        } else if (fechaMin.getYear() > LocalDate.now().getYear() || fechaMax.getYear() > LocalDate.now().getYear()) {
            throw new BadRequestException("El año de las fechas no puede ser mayor a " + LocalDate.now().getYear());
        }

        for(Auth auth : authRepository.findAllByEntreFechas(fecha_ini, fecha_ter)) {
            AuthDTO authDTO = mapper.toDTO(auth);
            authsDTO.add(authDTO);
        }

        return authsDTO;
    }

    public Auth save(Auth auth) {
        return authRepository.save(auth);
    }

    public Auth update(Long idAuth, Auth authNuevo) {
        Auth auth = authRepository.findById( idAuth ).orElseThrow(() -> new ResourceNotFoundException("Auth no encontrado"));

        auth.setEstado( authNuevo.getEstado() );
        auth.setFechaExpiracion( authNuevo.getFechaExpiracion() );

        return authRepository.save(auth);
    }

}
