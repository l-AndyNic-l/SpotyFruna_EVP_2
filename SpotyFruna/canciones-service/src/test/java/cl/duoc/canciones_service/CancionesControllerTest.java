package cl.duoc.canciones_service;

import cl.duoc.canciones_service.controller.CancionController;
import cl.duoc.canciones_service.dto.CancionDTO;
import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.service.CancionService;
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
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CancionController.class)
@DisplayName("Prueba para CancionesController")
public class CancionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CancionService cancionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Debe retornar lista de canciones cuando existen datos")
    void testFindAll() throws Exception {
        //ARANGE
        CancionDTO cancion = new CancionDTO();
        cancion.setId(1L);
        cancion.setTitulo("Tear my heart - Twenty one pilots");
        List<CancionDTO> canciones = new ArrayList<>();

        Mockito.when(cancionService.findAll()).thenReturn(canciones);

        //ACT
        mockMvc.perform(get("/api/v1/canciones"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].nombre").value("Tear my heart - Twenty one pilots"));

    }

    @Test
    @DisplayName("Debe retornar 204 No content cuando la lista esta vacía")
    void testFindAllEmpty() throws Exception {
        //ARANGE
        Mockito.when(cancionService.findAll()).thenReturn(new ArrayList<>());
        //ACT
        mockMvc.perform(get("/api/v1/canciones"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Debe crear canciones correctamente")
    void testSave() throws Exception {
        //ARANGE
        CancionDTO cancion = new CancionDTO();
        cancion.setTitulo("Mulberry Street - Twenty one pilots");

        Cancion cancionnueva = new Cancion();
        cancionnueva.setId(2L);
        cancionnueva.setTitulo("Tear my heart - Twenty one pilots");

        Mockito.when(cancionService.save(Mockito.any())).thenReturn(cancionnueva);

        //ACT y ASSERT
        mockMvc.perform(post("/api/v1/canciones")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(cancionnueva)))
                .andExpect(status().isCreated()) //
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.nombre").value("Mulberry Street - Twenty one pilots"));
    }

    @Test
    @DisplayName("Debe actualizar una canción correctamente")
    void testUpdate() throws Exception {
        //ARANGE
        Long idCancion = 2L; //Cancion existente para probar método

        Cancion cancionUpdate = new Cancion();
        cancionUpdate.setTitulo("Mulberry Street - Twenty one pilots");

        Mockito.when(cancionService.update(Mockito.eq(idCancion), Mockito.any()))
                .thenReturn(cancionUpdate);

        //ACT y ASSERT
        mockMvc.perform(put("/api/v1/canciones/{idCancion}", idCancion)
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(cancionUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Mulberry Street - Twenty one pilots"));
    }

    @Test
    @DisplayName("Debe eliminar una canción correctamente")
    void testDelete() throws Exception {
        Long idCancion = 1L;

        //Mockito.doNothing() para simular que ejecutó con exito porque el método es void y no devuelve nada
        Mockito.doNothing().when(cancionService).deleteById(idCancion);

        mockMvc.perform(delete("/api/v1/canciones/{idCancion}", idCancion))
                .andExpect(status().isNoContent());

    }


}
