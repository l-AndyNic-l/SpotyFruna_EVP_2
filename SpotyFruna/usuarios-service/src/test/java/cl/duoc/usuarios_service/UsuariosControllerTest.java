package cl.duoc.usuarios_service;
import cl.duoc.usuarios_service.controller.UsuarioController;
import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.service.UsuarioService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
@DisplayName("Pruebas para UsuarioController")
public class UsuariosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // Tu anotación moderna de Spring Boot para clonar el servicio
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Debe retornar todos los usuarios")
    void testFindAll() throws Exception {
        // 1. ARRANGE [cite: 35, 36]
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        List<UsuarioDTO> lista = Arrays.asList(usuarioDTO);
        Mockito.when(usuarioService.findAll()).thenReturn(lista);

        // 2. ACT & 3. ASSERT [cite: 35, 37, 38]
        mockMvc.perform(get("/api/v1/usuarios")) // Corregido el endpoint al de usuarios
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L)); // Sin casteo manual
    }

    @Test
    @DisplayName("Debe retornar 204 No content cuando no hay usuarios")
    void testAllEmpty() throws Exception {
        // 1. ARRANGE [cite: 35, 36]
        Mockito.when(usuarioService.findAll()).thenReturn(Collections.emptyList());

        // 2. ACT & 3. ASSERT [cite: 35, 37, 38]
        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Debe buscar usuarios por ID correctamente")
    void testFindById() throws Exception {
        // 1. ARRANGE [cite: 35, 36]
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(id);
        Mockito.when(usuarioService.findById(id)).thenReturn(usuarioDTO);

        // 2. ACT & 3. ASSERT [cite: 35, 37, 38]
        mockMvc.perform(get("/api/v1/usuarios/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @DisplayName("Debe buscar usuarios entre fechas correctamente")
    void testFindAllBetweenDate() throws Exception {
        // 1. ARRANGE [cite: 35, 36]
        LocalDate fechaMin = LocalDate.of(2026, 1, 1);
        LocalDate fechaMax = LocalDate.of(2026, 12, 31);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        List<UsuarioDTO> lista = Arrays.asList(usuarioDTO);

        Mockito.when(usuarioService.findAllBetweenDates(fechaMin, fechaMax)).thenReturn(lista);

        // 2. ACT & 3. ASSERT [cite: 35, 37, 38]
        mockMvc.perform(get("/api/v1/usuarios/between-dates")
                        .param("fecha-min", "2026-01-01")
                        .param("fecha-max", "2026-12-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    @DisplayName("Debe crear un usuario correctamente")
    void testSave() throws Exception {
        // 1. ARRANGE [cite: 35, 36]
        Usuario usuarioInput = new Usuario();
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setId(10L);

        Mockito.when(usuarioService.save(Mockito.any(Usuario.class))).thenReturn(usuarioNuevo);

        // 2. ACT & 3. ASSERT [cite: 35, 37, 38]
        mockMvc.perform(post("/api/v1/usuarios") // Usando el import estático correcto de MockMvc
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(usuarioInput)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L)); // Esperamos el ID 10L configurado en el mock
    }

    @Test
    @DisplayName("Debe actualizar un usuario correctamente (PUT)")
    void testUpdate() throws Exception {
        Long idExistente = 1L;

        Usuario usuarioInput = new Usuario();
        usuarioInput.setNombre("Eric");
        usuarioInput.setNickname("eric_dev");
        usuarioInput.setEmail("eric@duoc.cl");

        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setId(idExistente);
        usuarioActualizado.setNombre("Eric");
        usuarioActualizado.setNickname("eric_dev");

        Mockito.when(usuarioService.update(Mockito.eq(idExistente), Mockito.any(Usuario.class)))
                .thenReturn(usuarioActualizado);

        mockMvc.perform(put("/api/v1/usuarios/{idUsuario}", idExistente)
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(usuarioInput)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idExistente))
                .andExpect(jsonPath("$.nickname").value("eric_dev"));
    }

    @Test
    @DisplayName("Debe eliminar un usuario correctamente (DELETE)")
    void testDelete() throws Exception {
        Long idAEliminar = 1L;
        Mockito.doNothing().when(usuarioService).deleteById(idAEliminar);


        mockMvc.perform(delete("/api/v1/usuarios/{idUsuario}", idAEliminar))
                .andExpect(status().isNoContent());

        Mockito.verify(usuarioService, Mockito.times(1)).deleteById(idAEliminar);
    }
}