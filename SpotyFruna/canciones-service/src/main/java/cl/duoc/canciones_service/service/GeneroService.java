package cl.duoc.canciones_service.service;

import cl.duoc.canciones_service.exception.ConflictException;
import cl.duoc.canciones_service.exception.ResourceNotFoundException;
import cl.duoc.canciones_service.model.Genero;
import cl.duoc.canciones_service.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;



    public List<Genero> findAll() {
        return generoRepository.findAll();
    }

    public Genero findById(Long idGenero) {
        return generoRepository.findById(idGenero).orElseThrow(() -> new ResourceNotFoundException("Género no encontrado"));
    }

    public Genero save(Genero genero) {
        if (generoRepository.existsByNombre(genero.getNombre())) {
            throw new ConflictException("Ya existe un género con ese nombre");
        }
        return generoRepository.save(genero);
    }

    public Genero update(Long idGenero, Genero generoNuevo) {
        Genero genero = generoRepository.findById(idGenero).orElseThrow(() -> new ResourceNotFoundException("Género no encontrado"));

        if (generoRepository.existsByNombre(generoNuevo.getNombre())) {
            throw new ConflictException("Ya existe ese género");
        }
        genero.setNombre(generoNuevo.getNombre());

        return generoRepository.save(genero);
    }

    public void deleteById(Long idGenero) {
        if (!generoRepository.existsById(idGenero)) {
            throw new ResourceNotFoundException("Género no encontrado");
        }
            generoRepository.deleteById(idGenero);
    }

}
