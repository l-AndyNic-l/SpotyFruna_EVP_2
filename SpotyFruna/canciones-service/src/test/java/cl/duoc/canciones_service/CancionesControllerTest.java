package cl.duoc.canciones_service;

import cl.duoc.canciones_service.controller.CancionController;
import cl.duoc.canciones_service.dto.AlbumCancionDTO;
import cl.duoc.canciones_service.dto.AlbumDTO;
import cl.duoc.canciones_service.dto.CancionDTO;
import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.model.Genero;
import cl.duoc.canciones_service.service.CancionService;
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
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CancionController.class)
@DisplayName("Pruebas unitarias para CancionesController")
public class CancionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CancionService cancionService;

    private Cancion cancion;
    private Cancion cancionSinID;
    private CancionDTO cancionDTO;
    private AlbumDTO albumDTO;
    private AlbumCancionDTO albumCancionDTO;
    private Genero genero;

    @BeforeEach
    public void setUpGenero() {
        genero = new Genero(
                1L,
                "Rock"
        );
    }

    @BeforeEach
    public void setUpAlbumDTO() {
        albumDTO = new AlbumDTO(
                1L,
                "Queen",
                "Bohemian Rhapsody",
                "Sencillo de opera rock",
                "31/10/1975",
                "Sencillo"
        );
    }

    @BeforeEach
    public void setUpAlbumCancionDTO() {
        albumCancionDTO = new AlbumCancionDTO(
                1L,
                "Bohemian Rhapsody",
                "6:58"
        );
    }

    @BeforeEach
    public void setUpCancion() {
        cancion = new Cancion(
                1L,
                "Queen",
                "Bohemian Rhapsody",
                354L,
                LocalDate.of(1975, 10, 31),
                genero,
                8L
        );

        cancionSinID = new Cancion(
                null,
                "Queen",
                "Bohemian Rhapsody",
                354L,
                LocalDate.of(1975, 10, 31),
                genero,
                8L
        );
    }

    @BeforeEach
    public void setUpCancionDTO() {
        cancionDTO = new CancionDTO(
                1L,
                "Queen",
                "Bohemian Rhapsody",
                "6:20",
                "31/10/1975",
                genero.getNombre(),
                albumDTO.getNombre()
        );
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/v1/canciones - Debería retornar 200 OK y la lista de canciones")
    public void findAllCanciones() throws Exception {

        when(cancionService.findAll()).thenReturn(List.of(cancionDTO));

        mockMvc.perform(get("/api/v1/canciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].autor").value("Queen"))
                .andExpect(jsonPath("$[0].titulo").value("Bohemian Rhapsody"))
                .andExpect(jsonPath("$[0].duracion").value("6:20"))
                .andExpect(jsonPath("$[0].fechaLanzamiento").value("31/10/1975"))
                .andExpect(jsonPath("$[0].genero").value(genero.getNombre()))
                .andExpect(jsonPath("$[0].album").value(albumDTO.getNombre()));

    }

    @Test
    @DisplayName("GET /api/v1/canciones/{id} - Debería retornar 200 OK y la cancion según ID")
    public void findByIdCanciones() throws Exception {

        Long idCancion = 1L;
        when(cancionService.findById(idCancion)).thenReturn(cancionDTO);

        mockMvc.perform(get("/api/v1/canciones/" + idCancion))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.autor").value("Queen"))
                .andExpect(jsonPath("$.titulo").value("Bohemian Rhapsody"))
                .andExpect(jsonPath("$.duracion").value("6:20"))
                .andExpect(jsonPath("$.fechaLanzamiento").value("31/10/1975"))
                .andExpect(jsonPath("$.genero").value(genero.getNombre()))
                .andExpect(jsonPath("$.album").value(albumDTO.getNombre()));

    }

    @Test
    @DisplayName("GET /api/v1/canciones/album/{idAlbum} - Debería retornar 200 OK y la lista de canciones según album")
    public void findAllByIdAlbumCanciones() throws Exception {

        Long idAlbum = 1L;
        when(cancionService.findAllByIdAlbum(idAlbum)).thenReturn(List.of(albumCancionDTO));

        mockMvc.perform(get("/api/v1/canciones/album/" + idAlbum))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Bohemian Rhapsody"))
                .andExpect(jsonPath("$[0].duracion").value("6:58"));
    }

    @Test
    @DisplayName("GET /api/v1/canciones/autor/{autor} - Debería retornar 200 OK y la lista de canciones según autor")
    public void findAllByIdAutorCanciones() throws Exception {

        String autor = "Queen";
        when(cancionService.findAllByAutor(autor)).thenReturn(List.of(cancionDTO));

        mockMvc.perform(get("/api/v1/canciones/autor/" + autor))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].autor").value("Queen"))
                .andExpect(jsonPath("$[0].titulo").value("Bohemian Rhapsody"))
                .andExpect(jsonPath("$[0].duracion").value("6:20"))
                .andExpect(jsonPath("$[0].fechaLanzamiento").value("31/10/1975"))
                .andExpect(jsonPath("$[0].genero").value(genero.getNombre()))
                .andExpect(jsonPath("$[0].album").value(albumDTO.getNombre()));
    }

    @Test
    @DisplayName("GET /api/v1/canciones/genero/{idGenero} - Debería retornar 200 OK y la lista de canciones según genero")
    public void findAllByIdGeneroCanciones() throws Exception {

        Long idGenero = 1L;
        when(cancionService.findAllByGenero(idGenero)).thenReturn(List.of(cancionDTO));

        mockMvc.perform(get("/api/v1/canciones/genero/" + idGenero))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].autor").value("Queen"))
                .andExpect(jsonPath("$[0].titulo").value("Bohemian Rhapsody"))
                .andExpect(jsonPath("$[0].duracion").value("6:20"))
                .andExpect(jsonPath("$[0].fechaLanzamiento").value("31/10/1975"))
                .andExpect(jsonPath("$[0].genero").value(genero.getNombre()))
                .andExpect(jsonPath("$[0].album").value(albumDTO.getNombre()));
    }

    @Test
    @DisplayName("GET /api/v1/canciones/between-dates - Debería retornar 200 OK y la lista de canciones según su rango de fecha de lanzamiento")
    public void findAllBetweenDatesUsuario() throws Exception {

        LocalDate fechaMin = LocalDate.of(1970, 1, 1);
        LocalDate fechaMax = LocalDate.of(1980, 1, 1);
        when(cancionService.findAllBetweenDates(fechaMin, fechaMax)).thenReturn(List.of(cancionDTO));

        mockMvc.perform(get("/api/v1/canciones/between-dates").param("fecha-min", String.valueOf(fechaMin)).param("fecha-max", String.valueOf(fechaMax)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].autor").value("Queen"))
                .andExpect(jsonPath("$[0].titulo").value("Bohemian Rhapsody"))
                .andExpect(jsonPath("$[0].duracion").value("6:20"))
                .andExpect(jsonPath("$[0].fechaLanzamiento").value("31/10/1975"))
                .andExpect(jsonPath("$[0].genero").value(genero.getNombre()))
                .andExpect(jsonPath("$[0].album").value(albumDTO.getNombre()));

    }

    @Test
    @DisplayName("GET /api/v1/canciones/between-durations - Debería retornar 200 OK y la lista de canciones según su rango de duracion")
    public void findAllBetweenDurationUsuario() throws Exception {

        Long duracionMin = 1L;
        Long duracionMax = 500L;
        when(cancionService.findAllBetweenDuration(duracionMin, duracionMax)).thenReturn(List.of(cancionDTO));

        mockMvc.perform(get("/api/v1/canciones/between-durations").param("duracion-min", String.valueOf(duracionMin)).param("duracion-max", String.valueOf(duracionMax)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].autor").value("Queen"))
                .andExpect(jsonPath("$[0].titulo").value("Bohemian Rhapsody"))
                .andExpect(jsonPath("$[0].duracion").value("6:20"))
                .andExpect(jsonPath("$[0].fechaLanzamiento").value("31/10/1975"))
                .andExpect(jsonPath("$[0].genero").value(genero.getNombre()))
                .andExpect(jsonPath("$[0].album").value(albumDTO.getNombre()));

    }

    @Test
    @DisplayName("POST /api/v1/canciones - Debería retornar 201 CREATED y la cancion creado")
    void postCancion() throws Exception {

        when(cancionService.save(any(Cancion.class))).thenReturn(cancion);

        mockMvc.perform(post("/api/v1/canciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cancionSinID)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.autor").value("Queen"))
                .andExpect(jsonPath("$.titulo").value("Bohemian Rhapsody"))
                .andExpect(jsonPath("$.duracion").value(354))
                .andExpect(jsonPath("$.fechaLanzamiento").value("1975-10-31"))
                .andExpect(jsonPath("$.genero").value(cancion.getGenero()))
                .andExpect(jsonPath("$.idAlbum").value(cancion.getIdAlbum()));
    }

    @Test
    @DisplayName("DELETE /api/v1/canciones/{id} - Debería retornar 204 NO CONTENT")
    public void deleteCancion() throws Exception {

        Long idCancion = 1L;
        when(cancionService.findById(idCancion)).thenReturn(cancionDTO);

        mockMvc.perform(delete("/api/v1/canciones/" + idCancion))
                .andExpect(status().isNoContent());
    }

}
