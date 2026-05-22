package cl.duoc.auth_service.service;

import cl.duoc.auth_service.exception.ConflictException;
import cl.duoc.auth_service.exception.ResourceNotFoundException;
import cl.duoc.auth_service.model.Estado;
import cl.duoc.auth_service.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Estado findByNombre(String nombre) {
        Estado estado = estadoRepository.findByNombre(nombre);
        if (estado == null) {
            throw new ResourceNotFoundException("Estado no encontrado");
        }
        return estado;
    }

    public Estado save(Estado estado) {
        if (estadoRepository.existsByNombre(estado.getNombre())) {
            throw new ConflictException("Ya existe un estado con ese nombre");
        }
        return estadoRepository.save(estado);
    }

    public Estado update(Long idEstado, Estado estadoNuevo) {
        Estado estado = estadoRepository.findById(idEstado).orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));

        if (estadoRepository.existsByNombre(estadoNuevo.getNombre())) {
            throw new ConflictException("Ya existe un estado con ese nombre");
        }
        estado.setNombre(estadoNuevo.getNombre());

        return estadoRepository.save(estado);
    }

    public void deleteById(Long idEstado) {
        if (!estadoRepository.existsById(idEstado)) {
            throw new ResourceNotFoundException("Estado no encontrado");
        }
        estadoRepository.deleteById(idEstado);
    }

}
