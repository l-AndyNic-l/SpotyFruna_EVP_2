package cl.duoc.auth_service.service;

import cl.duoc.auth_service.dto.AuthDTO;
import cl.duoc.auth_service.mapper.AuthMapper;
import cl.duoc.auth_service.model.Auth;
import cl.duoc.auth_service.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthMapper mapper;

    public List<AuthDTO>  findAll() {
        List<AuthDTO> listado = new ArrayList<>();

        for (Auth a : authRepository.findAll()) {
            AuthDTO a_dto = mapper.toDTO(a);
            listado.add(a_dto);
        }

        return listado;
    }

    public List<AuthDTO> findAllByUsuario(Long id) {
        List<AuthDTO> listado = new ArrayList<>();

        for (Auth a : authRepository.findAllByUsuario(id)) {
            AuthDTO a_dto = mapper.toDTO(a);
            listado.add(a_dto);
        }

        return listado;
    }

    public List<AuthDTO> findAllByAnio(int anio) {
        List<AuthDTO> listado = new ArrayList<>();

        for(Auth a : authRepository.findAllByAnio(anio)) {
            AuthDTO a_dto = mapper.toDTO(a);
            listado.add(a_dto);
        }

        return listado;
    }

    public List<AuthDTO> findAllByMes(int mes, int anio) {
        List<AuthDTO> listado = new ArrayList<>();

        for(Auth a : authRepository.findAllByMes(mes, anio)) {
            AuthDTO a_dto = mapper.toDTO(a);
            listado.add(a_dto);
        }

        return listado;
    }

    public List<AuthDTO> findAllByDia(int dia, int mes, int anio) {
        List<AuthDTO> listado = new ArrayList<>();

        for(Auth a : authRepository.findAllByDia(dia, mes, anio)) {
            AuthDTO a_dto = mapper.toDTO(a);
            listado.add(a_dto);
        }

        return listado;
    }

    public List<AuthDTO> findAllByEntreFechas(String fecha_ini, String fecha_ter) {
        List<AuthDTO> listado = new ArrayList<>();

        for(Auth r : authRepository.findAllByEntreFechas(fecha_ini, fecha_ter)) {
            AuthDTO r_dto = mapper.toDTO(r);
            listado.add(r_dto);
        }

        return listado;
    }

    public Auth save(Auth a) {
        return authRepository.save(a);
    }

}
