package cl.duoc.suscripciones_service.service;

import cl.duoc.suscripciones_service.dto.PlanDTO;
import cl.duoc.suscripciones_service.exception.BadRequestException;
import cl.duoc.suscripciones_service.exception.ConflictException;
import cl.duoc.suscripciones_service.exception.ResourceNotFoundException;
import cl.duoc.suscripciones_service.mapper.PlanMapper;
import cl.duoc.suscripciones_service.model.Plan;
import cl.duoc.suscripciones_service.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanMapper mapper;

    public List<PlanDTO> findAll() {
        List<PlanDTO> planDTOS = new ArrayList<>();

        for (Plan plan : planRepository.findAll()) {
            planDTOS.add(mapper.toDTO(plan));
        }

        return planDTOS;
    }

    public PlanDTO findById(Long idPlan) {
        if (idPlan == null || idPlan < 1) {
            throw new BadRequestException("idPlan no puede ser menor a 1 ni nulo");
        }

        Plan plan = planRepository.findById(idPlan).orElseThrow(() -> new ResourceNotFoundException("Plan no encontrado"));

        return mapper.toDTO(plan);
    }

    public Plan save(Plan plan) {
        if (planRepository.existsByNombre(plan.getNombre())) {
            throw new ConflictException("Ya existe un plan con ese nombre");
        }
        return planRepository.save(plan);
    }

    public Plan update(Long idPlan, Plan planNuevo) {
        Plan plan = planRepository.findById(idPlan).orElseThrow(() -> new ResourceNotFoundException("Plan no encontrado"));

        if (planRepository.existsByNombre(planNuevo.getNombre())) {
            throw new ConflictException("Ya existe un plan con ese nombre");
        }
        plan.setNombre(planNuevo.getNombre());
        plan.setPrecio(planNuevo.getPrecio());
        plan.setAnuncios(planNuevo.getAnuncios());
        plan.setTamanioDescargas(planNuevo.getTamanioDescargas());

        return planRepository.save(plan);
    }

    public void deleteById(Long idPlan) {
        if (!planRepository.existsById(idPlan)) {
            throw new ResourceNotFoundException("Plan no encontrado");
        }
        planRepository.deleteById(idPlan);
    }

}
