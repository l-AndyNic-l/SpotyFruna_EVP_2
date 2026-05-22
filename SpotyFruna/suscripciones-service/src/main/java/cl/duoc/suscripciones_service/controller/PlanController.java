package cl.duoc.suscripciones_service.controller;

import cl.duoc.suscripciones_service.dto.PlanDTO;
import cl.duoc.suscripciones_service.model.Plan;
import cl.duoc.suscripciones_service.service.PlanService;
import feign.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/planes")
public class PlanController {

    @Autowired
    private PlanService planService;



    @GetMapping
    public ResponseEntity<List<PlanDTO>> findAll() {
        List<PlanDTO> planes = planService.findAll();

        if (planes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(planes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> findById(@PathVariable Long idPlan) {
        PlanDTO plan = planService.findById(idPlan);

        return ResponseEntity.ok(plan);
    }

    @PostMapping
    public ResponseEntity<Plan> save(@Valid @RequestBody Plan plan) {
        Plan planInstanciado = planService.save(plan);
        return new ResponseEntity<>(planInstanciado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plan> update(@PathVariable Long idPlan, @Valid @RequestBody Plan plan) {
        Plan planInstanciado = planService.update(idPlan, plan);
        return ResponseEntity.ok(planInstanciado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long idPlan) {
        planService.deleteById(idPlan);
        return ResponseEntity.noContent().build();
    }

}
