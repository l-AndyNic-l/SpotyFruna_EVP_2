package cl.duoc.suscripciones_service;

import cl.duoc.suscripciones_service.controller.SuscripcionController;
import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.model.Suscripcion;
import cl.duoc.suscripciones_service.service.SuscripcionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuscripcionController.class)
@DisplayName("Pruebas Unitarias para SuscripcionController")
public class SuscripcionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SuscripcionService suscripcionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Debe retornar todas las suscripciones")
    void testFindAll() throws Exception {
        SuscripcionDTO dto = new SuscripcionDTO();
        dto.setId(1L);
        Mockito.when(suscripcionService.findAll()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/api/v1/suscripciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    @DisplayName("Debe retornar 204 si no hay suscripciones")
    void testFindAllEmpty() throws Exception {
        Mockito.when(suscripcionService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/suscripciones"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Debe buscar por ID correctamente")
    void testFindById() throws Exception {
        Long id = 1L;
        SuscripcionDTO dto = new SuscripcionDTO();
        dto.setId(id);
        Mockito.when(suscripcionService.findById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/suscripciones/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @DisplayName("Debe buscar por Plan")
    void testFindByPlan() throws Exception {
        Long idPlan = 1L;
        SuscripcionDTO dto = new SuscripcionDTO();
        dto.setId(100L);
        Mockito.when(suscripcionService.findAllByPlan(idPlan)).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/api/v1/suscripciones/plan/{idPlan}", idPlan))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(100L));
    }

    @Test
    @DisplayName("Debe buscar entre fechas")
    void testBetweenDates() throws Exception {
        LocalDate fMin = LocalDate.of(2026, 1, 1);
        LocalDate fMax = LocalDate.of(2026, 12, 31);
        Mockito.when(suscripcionService.findAllBetweenDates(fMin, fMax))
                .thenReturn(Arrays.asList(new SuscripcionDTO()));

        mockMvc.perform(get("/api/v1/suscripciones/between-dates")
                        .param("fecha-min", "2026-01-01")
                        .param("fecha-max", "2026-12-31"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Debe guardar una suscripción (POST)")
    void testSave() throws Exception {
        Suscripcion input = new Suscripcion();
        input.setActivado(true);

        Suscripcion output = new Suscripcion();
        output.setId(10L);

        Mockito.when(suscripcionService.save(Mockito.any(Suscripcion.class))).thenReturn(output);

        mockMvc.perform(post("/api/v1/suscripciones")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    @DisplayName("Debe actualizar una suscripción (PUT)")
    void testUpdate() throws Exception {
        Long id = 1L;
        Suscripcion input = new Suscripcion();
        Suscripcion output = new Suscripcion();
        output.setId(id);

        Mockito.when(suscripcionService.update(Mockito.eq(id), Mockito.any(Suscripcion.class))).thenReturn(output);

        mockMvc.perform(put("/api/v1/suscripciones/{id}", id)
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @DisplayName("Debe eliminar una suscripción (DELETE)")
    void testDelete() throws Exception {
        Long id = 1L;
        Mockito.doNothing().when(suscripcionService).deleteById(id);

        mockMvc.perform(delete("/api/v1/suscripciones/{id}", id))
                .andExpect(status().isNoContent());
    }
}