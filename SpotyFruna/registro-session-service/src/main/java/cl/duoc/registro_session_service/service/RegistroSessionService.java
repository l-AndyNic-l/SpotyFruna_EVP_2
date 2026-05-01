package cl.duoc.registro_session_service.service;

import cl.duoc.registro_session_service.dto.RegistroSessionDTO;
import cl.duoc.registro_session_service.mapper.RegistroSessionMapper;
import cl.duoc.registro_session_service.model.RegistroSession;
import cl.duoc.registro_session_service.repository.RegistroSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistroSessionService {

    @Autowired
    private RegistroSessionRepository registroSessionRepository;

    @Autowired
    private RegistroSessionMapper mapper;

    public List<RegistroSessionDTO>  findAll() {
        List<RegistroSessionDTO> listado = new ArrayList<>();

        for (RegistroSession r : registroSessionRepository.findAll()) {
            RegistroSessionDTO r_dto = mapper.toDTO(r);
            listado.add(r_dto);
        }

        return listado;
    }

    public List<RegistroSessionDTO> findAllByUsuario(Long id) {
        List<RegistroSessionDTO> listado = new ArrayList<>();

        for (RegistroSession r : registroSessionRepository.findAllByUsuario(id)) {
            RegistroSessionDTO r_dto = mapper.toDTO(r);
            listado.add(r_dto);
        }

        return listado;
    }

    public List<RegistroSessionDTO> findAllByAnio(int anio) {
        List<RegistroSessionDTO> listado = new ArrayList<>();

        for(RegistroSession r : registroSessionRepository.findAllByAnio(anio)) {
            RegistroSessionDTO r_dto = mapper.toDTO(r);
            listado.add(r_dto);
        }

        return listado;
    }

    public List<RegistroSessionDTO> findAllByMes(int mes, int anio) {
        List<RegistroSessionDTO> listado = new ArrayList<>();

        for(RegistroSession r : registroSessionRepository.findAllByMes(mes, anio)) {
            RegistroSessionDTO r_dto = mapper.toDTO(r);
            listado.add(r_dto);
        }

        return listado;
    }

    public List<RegistroSessionDTO> findAllByDia(int dia, int mes, int anio) {
        List<RegistroSessionDTO> listado = new ArrayList<>();

        for(RegistroSession r : registroSessionRepository.findAllByDia(dia, mes, anio)) {
            RegistroSessionDTO r_dto = mapper.toDTO(r);
            listado.add(r_dto);
        }

        return listado;
    }

    public List<RegistroSessionDTO> findAllByEntreFechas(String fecha_ini, String fecha_ter) {
        List<RegistroSessionDTO> listado = new ArrayList<>();

        for(RegistroSession r : registroSessionRepository.findAllByEntreFechas(fecha_ini, fecha_ter)) {
            RegistroSessionDTO r_dto = mapper.toDTO(r);
            listado.add(r_dto);
        }

        return listado;
    }

    public RegistroSession save(RegistroSession r) {
        return registroSessionRepository.save(r);
    }

}
