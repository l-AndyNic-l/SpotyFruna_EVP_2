package cl.duoc.seguridades_service.service;

import cl.duoc.seguridades_service.model.RegistroSession;
import cl.duoc.seguridades_service.repository.RegistroSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegistroSessionService {

    @Autowired
    private RegistroSessionRepository registroSessionRepository;

    public RegistroSession save(RegistroSession r) {
        return registroSessionRepository.save(r);
    }

}
