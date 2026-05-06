package cl.duoc.playlists_service.service;

import cl.duoc.playlists_service.repository.PrivacidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivacidadService {

    @Autowired
    private PrivacidadRepository privacidadRepository;
}
