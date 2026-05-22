package cl.duoc.playlists_service.service;

import cl.duoc.playlists_service.exception.ConflictException;
import cl.duoc.playlists_service.exception.ResourceNotFoundException;
import cl.duoc.playlists_service.model.Privacidad;
import cl.duoc.playlists_service.repository.PrivacidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrivacidadService {

    @Autowired
    private PrivacidadRepository privacidadRepository;



    public List<Privacidad> findAll() {
        return privacidadRepository.findAll();
    }

    public Privacidad findById(Long idPrivacidad) {
        return privacidadRepository.findById(idPrivacidad).orElseThrow(() -> new ResourceNotFoundException("Privacidad no encontrada"));
    }

    public Privacidad save(Privacidad privacidad) {
        if (privacidadRepository.existsByNombre(privacidad.getNombre())) {
            throw new ConflictException("Ya existe una privacidad con ese nombre");
        }
        return privacidadRepository.save(privacidad);
    }

    public Privacidad update(Long idPrivacidad, Privacidad privacidadNueva) {
        Privacidad privacidad = privacidadRepository.findById(idPrivacidad).orElseThrow(() -> new ResourceNotFoundException("Privacidad no encontrada"));

        if (privacidadRepository.existsByNombre(privacidadNueva.getNombre())) {
            throw new ConflictException("Ya existe una privacidad con ese nombre");
        }
        privacidad.setNombre(privacidadNueva.getNombre());

        return privacidadRepository.save(privacidad);
    }

    public void deleteById(Long idPrivacidad) {
        if (!privacidadRepository.existsById(idPrivacidad)) {
            throw new ResourceNotFoundException("Privacidad no encontrada");
        }
        privacidadRepository.deleteById(idPrivacidad);
    }

}
