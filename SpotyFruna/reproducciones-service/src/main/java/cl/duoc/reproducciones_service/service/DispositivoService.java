package cl.duoc.reproducciones_service.service;

import cl.duoc.reproducciones_service.exception.BadRequestException;
import cl.duoc.reproducciones_service.exception.ResourceNotFoundException;
import cl.duoc.reproducciones_service.model.Dispositivo;
import cl.duoc.reproducciones_service.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;



    public List<Dispositivo> findAll() {
        return dispositivoRepository.findAll();
    }

    public Dispositivo findByNombre(String nombre) {
        if (nombre == null) {
            throw new BadRequestException("Nombre no puede ser nulo");
        }

        Dispositivo dispositivo = dispositivoRepository.findByNombre(nombre);

        if (dispositivo == null) {
            throw new ResourceNotFoundException("Dispositivo no encontrado");
        }
        return dispositivo;
    }

    public Dispositivo save(Dispositivo dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo update(Long idDispositivo,  Dispositivo dispositivoNuevo) {
        Dispositivo dispositivo = dispositivoRepository.findById(idDispositivo).orElseThrow(() -> new ResourceNotFoundException("Dispositivo no encontrado"));

        dispositivo.setNombre(dispositivoNuevo.getNombre());

        return dispositivoRepository.save(dispositivo);
    }

    public void delete(Long idDispositivo) {
        if(!dispositivoRepository.existsById(idDispositivo)) {
            throw new ResourceNotFoundException("Dispositivo no encontrado");
        }
        dispositivoRepository.deleteById(idDispositivo);
    }

}
