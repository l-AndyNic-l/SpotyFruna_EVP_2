package cl.duoc.suscripciones_service;

import cl.duoc.suscripciones_service.controller.SuscripcionController;
import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.dto.UsuarioDTO;
import cl.duoc.suscripciones_service.model.Plan;
import cl.duoc.suscripciones_service.model.Suscripcion;
import cl.duoc.suscripciones_service.service.SuscripcionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuscripcionController.class)
@DisplayName("Pruebas unitarias para SuscripcionesController")
public class SuscripcionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SuscripcionService suscripcionService;

    private Suscripcion suscripcion;
    private Suscripcion suscripcionSinID;
    private SuscripcionDTO suscripcionDTO;
    private UsuarioDTO usuarioDTO;
    private Plan plan;

    @BeforeEach
    public void setUpUsuarioDTO() {
        usuarioDTO = new UsuarioDTO(
                1L,
                "Freddy Mercury",
                "Queen",
                "freddy.queen@gmail.com",
                40,
                1232342334,
                "Artista"
        );
    }

    @BeforeEach
    public void setUpPlan() {
        plan = new Plan(
                1L,
                "Familiar",
                11990,
                false,
                1000.0
        );
    }

    @BeforeEach
    public void setUpSuscripcion() {
        suscripcion = new Suscripcion(
                1L,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2026, 1, 1),
                false,
                plan,
                1L
        );

        suscripcionSinID = new Suscripcion(
                null,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2026, 1, 1),
                false,
                plan,
                1L
        );
    }

    @BeforeEach
    public void setUpSuscripcionDTO() {
        suscripcionDTO = new SuscripcionDTO(
                1L,
                "01/01/2024",
                "01/01/2026",
                "No Activa",
                "Familiar",
                "Freddy Mercury"
        );
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/v1/suscripciones - Debería retornar 200 OK y la lista de suscripciones")
    public void findAllSuscripciones() throws Exception {

        when(suscripcionService.findAll()).thenReturn(List.of(suscripcionDTO));

        mockMvc.perform(get("/api/v1/suscripciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fechaInicio").value("01/01/2024"))
                .andExpect(jsonPath("$[0].fechaTermino").value("01/01/2026"))
                .andExpect(jsonPath("$[0].activado").value("No Activa"))
                .andExpect(jsonPath("$[0].plan").value(plan.getNombre()))
                .andExpect(jsonPath("$[0].usuario").value(usuarioDTO.getNombreCompleto()));

    }

    @Test
    @DisplayName("GET /api/v1/suscripciones/between-dates - Debería retornar 200 OK y la lista de suscripciones según su rango de fecha")
    public void findAllBetweenDatesSuscripciones() throws Exception {

        LocalDate fechaMin = LocalDate.of(2020, 1, 1);
        LocalDate fechaMax = LocalDate.of(2026, 1, 1);
        when(suscripcionService.findAllBetweenDates(fechaMin, fechaMax)).thenReturn(List.of(suscripcionDTO));

        mockMvc.perform(get("/api/v1/suscripciones/between-dates").param("fecha-min", String.valueOf(fechaMin)).param("fecha-max", String.valueOf(fechaMax)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fechaInicio").value("01/01/2024"))
                .andExpect(jsonPath("$[0].fechaTermino").value("01/01/2026"))
                .andExpect(jsonPath("$[0].activado").value("No Activa"))
                .andExpect(jsonPath("$[0].plan").value(plan.getNombre()))
                .andExpect(jsonPath("$[0].usuario").value(usuarioDTO.getNombreCompleto()));

    }

    @Test
    @DisplayName("GET /api/v1/suscripciones/plan/{idPlan} - Debería retornar 200 OK y la lista de suscripciones según el plan")
    public void findByPlanSuscripciones() throws Exception {

        Long idPlan = 1L;
        when(suscripcionService.findAllByPlan(idPlan)).thenReturn(List.of(suscripcionDTO));

        mockMvc.perform(get("/api/v1/suscripciones/plan/" + idPlan))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fechaInicio").value("01/01/2024"))
                .andExpect(jsonPath("$[0].fechaTermino").value("01/01/2026"))
                .andExpect(jsonPath("$[0].activado").value("No Activa"))
                .andExpect(jsonPath("$[0].plan").value(plan.getNombre()))
                .andExpect(jsonPath("$[0].usuario").value(usuarioDTO.getNombreCompleto()));

    }

    @Test
    @DisplayName("GET /api/v1/suscripciones/activado - Debería retornar 200 OK y la lista de suscripciones según su actividad")
    public void findByActivadoSuscripciones() throws Exception {

        Boolean activado = true;
        when(suscripcionService.findAllByActivado(activado)).thenReturn(List.of(suscripcionDTO));

        mockMvc.perform(get("/api/v1/suscripciones/activado").param("activado", String.valueOf(activado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fechaInicio").value("01/01/2024"))
                .andExpect(jsonPath("$[0].fechaTermino").value("01/01/2026"))
                .andExpect(jsonPath("$[0].activado").value("No Activa"))
                .andExpect(jsonPath("$[0].plan").value(plan.getNombre()))
                .andExpect(jsonPath("$[0].usuario").value(usuarioDTO.getNombreCompleto()));
    }

    @Test
    @DisplayName("GET /api/v1/suscripciones/{id} - Debería retornar 200 OK y la suscripcion según ID")
    public void findByIdSuscripcion() throws Exception {

        Long idSuscripcion = 1L;
        when(suscripcionService.findById(idSuscripcion)).thenReturn(suscripcionDTO);

        mockMvc.perform(get("/api/v1/suscripciones/" + idSuscripcion))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fechaInicio").value("01/01/2024"))
                .andExpect(jsonPath("$.fechaTermino").value("01/01/2026"))
                .andExpect(jsonPath("$.activado").value("No Activa"))
                .andExpect(jsonPath("$.plan").value(plan.getNombre()))
                .andExpect(jsonPath("$.usuario").value(usuarioDTO.getNombreCompleto()));

    }

    @Test
    @DisplayName("POST /api/v1/suscripciones - Debería retornar 201 CREATED y la suscripcion creado")
    void postSuscripcion() throws Exception {

        when(suscripcionService.save(any(Suscripcion.class))).thenReturn(suscripcion);

        mockMvc.perform(post("/api/v1/suscripciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(suscripcionSinID)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fechaInicio").value("2024-01-01"))
                .andExpect(jsonPath("$.fechaTermino").value("2026-01-01"))
                .andExpect(jsonPath("$.activado").value(false))
                .andExpect(jsonPath("$.plan").value(suscripcion.getPlan()))
                .andExpect(jsonPath("$.idUsuario").value(suscripcion.getIdUsuario()));

    }

    @Test
    @DisplayName("DELETE /api/v1/suscripciones/{id} - Debería retornar 204 NO CONTENT")
    public void deleteSuscripcion() throws Exception {
        Long idSuscripcion = 1L;

        Mockito.doNothing().when(suscripcionService).deleteById(idSuscripcion);

        mockMvc.perform(delete("/api/v1/suscripciones/" + idSuscripcion))
                .andExpect(status().isNoContent());
    }

}