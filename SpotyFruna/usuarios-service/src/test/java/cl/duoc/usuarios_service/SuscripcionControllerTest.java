package cl.duoc.usuarios_service;

import cl.duoc.suscripciones_service.controller.SuscripcionController;
import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.model.Suscripcion;
import cl.duoc.suscripciones_service.service.SuscripcionService;
import org.junit.jupiter.api.DisplayName;
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
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuscripcionController.class)
@DisplayName("Pruebas para SuscripcionController")
public class SuscripcionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SuscripcionService  suscripcionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Debe retornar todas las suscripciones")
    void testFindAll() throws Exception {
        SuscripcionDTO suscripcionDTO = new SuscripcionDTO();
        suscripcionDTO.setId(1L);
        List<SuscripcionDTO> lista = Arrays.asList(suscripcionDTO);
        Mockito.when(suscripcionService.findAll()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/suscripciones"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1L));

    }

    


}
