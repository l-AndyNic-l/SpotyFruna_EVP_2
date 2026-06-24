package cl.duoc.usuarios_service;

import cl.duoc.usuarios_service.controller.UsuarioController;
import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.model.TipoUsuario;
import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
@DisplayName("Pruebas unitarias para UsuarioController")
public class UsuariosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    private Usuario usuario;
    private Usuario usuarioSinID;
    private UsuarioDTO usuarioDTO;
    private TipoUsuario tipoUsuario;

    @BeforeEach
    public void setUpTipoUsuario() {
        tipoUsuario = new TipoUsuario(
                1L,
                "Artista"
        );
    }

    @BeforeEach
    public void setUpUsuario() {
        usuario = new Usuario(
                1L,
                "Freddy",
                "Mercury",
                "Queen",
                "freddy.queen@gmail.com",
                "123456789",
                LocalDate.of(1970, 1, 1),
                1232342334,
                tipoUsuario
        );

        usuarioSinID = new Usuario(
                null,
                "Freddy",
                "Mercury",
                "Queen",
                "freddy.queen@gmail.com",
                "123456789",
                LocalDate.of(1970, 1, 1),
                1232342334,
                tipoUsuario
        );
    }

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

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/v1/usuarios - Debería retornar 200 OK y la lista de usuarios")
    public void findAllUsuarios() throws Exception {

        when(usuarioService.findAll()).thenReturn(List.of(usuarioDTO));

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreCompleto").value("Freddy Mercury"))
                .andExpect(jsonPath("$[0].nickname").value("Queen"))
                .andExpect(jsonPath("$[0].email").value("freddy.queen@gmail.com"))
                .andExpect(jsonPath("$[0].edad").value(40))
                .andExpect(jsonPath("$[0].celular").value(1232342334))
                .andExpect(jsonPath("$[0].tipoUsuario").value("Artista"));

    }

    @Test
    @DisplayName("GET /api/v1/usuarios/tipo-usuario - Debería retornar 200 OK y la lista de usuarios según su tipo")
    public void findByTipoUsuario() throws Exception {

        Long tipoUsuarioId = 1L;
        when(usuarioService.findAllByTipoUsuario(tipoUsuarioId)).thenReturn(List.of(usuarioDTO));

        mockMvc.perform(get("/api/v1/usuarios/tipo-usuario/" + tipoUsuarioId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreCompleto").value("Freddy Mercury"))
                .andExpect(jsonPath("$[0].nickname").value("Queen"))
                .andExpect(jsonPath("$[0].email").value("freddy.queen@gmail.com"))
                .andExpect(jsonPath("$[0].edad").value(40))
                .andExpect(jsonPath("$[0].celular").value(1232342334))
                .andExpect(jsonPath("$[0].tipoUsuario").value("Artista"));

    }

    @Test
    @DisplayName("GET /api/v1/usuarios/nickname - Debería retornar 200 OK y el usuario según nickname")
    public void findByNicknameUsuario() throws Exception {

        String nickname = "Queen";
        when(usuarioService.findAllByNickname(nickname)).thenReturn(List.of(usuarioDTO));

        mockMvc.perform(get("/api/v1/usuarios/nickname").param("nickname", nickname))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreCompleto").value("Freddy Mercury"))
                .andExpect(jsonPath("$[0].nickname").value("Queen"))
                .andExpect(jsonPath("$[0].email").value("freddy.queen@gmail.com"))
                .andExpect(jsonPath("$[0].edad").value(40))
                .andExpect(jsonPath("$[0].celular").value(1232342334))
                .andExpect(jsonPath("$[0].tipoUsuario").value("Artista"));

    }

    @Test
    @DisplayName("GET /api/v1/usuarios/between-dates - Debería retornar 200 OK y la lista de usuarios según su rango de fecha")
    public void findAllBetweenDatesUsuario() throws Exception {

        LocalDate fechaMin = LocalDate.of(1965, 1, 1);
        LocalDate fechaMax = LocalDate.of(1975, 1, 1);
        when(usuarioService.findAllBetweenDates(fechaMin, fechaMax)).thenReturn(List.of(usuarioDTO));

        mockMvc.perform(get("/api/v1/usuarios/between-dates").param("fecha-min", String.valueOf(fechaMin)).param("fecha-max", String.valueOf(fechaMax)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreCompleto").value("Freddy Mercury"))
                .andExpect(jsonPath("$[0].nickname").value("Queen"))
                .andExpect(jsonPath("$[0].email").value("freddy.queen@gmail.com"))
                .andExpect(jsonPath("$[0].edad").value(40))
                .andExpect(jsonPath("$[0].celular").value(1232342334))
                .andExpect(jsonPath("$[0].tipoUsuario").value("Artista"));

    }

    @Test
    @DisplayName("GET /api/v1/usuarios/{id} - Debería retornar 200 OK y el usuario según ID")
    public void findByIdUsuario() throws Exception {

        Long idUsuario = 1L;
        when(usuarioService.findById(idUsuario)).thenReturn(usuarioDTO);

        mockMvc.perform(get("/api/v1/usuarios/" + idUsuario))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreCompleto").value("Freddy Mercury"))
                .andExpect(jsonPath("$.nickname").value("Queen"))
                .andExpect(jsonPath("$.email").value("freddy.queen@gmail.com"))
                .andExpect(jsonPath("$.edad").value(40))
                .andExpect(jsonPath("$.celular").value(1232342334))
                .andExpect(jsonPath("$.tipoUsuario").value("Artista"));

    }

    @Test
    @DisplayName("POST /api/v1/usuarios - Debería retornar 201 CREATED y el usuario creado")
    void postUsuario() throws Exception {

        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioSinID)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Freddy"))
                .andExpect(jsonPath("$.apellido").value("Mercury"))
                .andExpect(jsonPath("$.nickname").value("Queen"))
                .andExpect(jsonPath("$.email").value("freddy.queen@gmail.com"))
                .andExpect(jsonPath("$.password").value("123456789"))
                .andExpect(jsonPath("$.fechaNacimiento").value("1970-01-01"))
                .andExpect(jsonPath("$.celular").value(1232342334))
                .andExpect(jsonPath("$.tipoUsuario").value(usuario.getTipoUsuario()));
    }

    @Test
    @DisplayName("DELETE /api/v1/usuarios/{id} - Debería retornar 204 NO CONTENT")
    public void deleteUsuario() throws Exception {

        Long idUsuario = 1L;
        when(usuarioService.findById(idUsuario)).thenReturn(usuarioDTO);

        mockMvc.perform(delete("/api/v1/usuarios/" + idUsuario))
                .andExpect(status().isNoContent());
    }
}