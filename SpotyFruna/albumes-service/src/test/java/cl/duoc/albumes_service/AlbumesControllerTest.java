package cl.duoc.albumes_service;

import cl.duoc.albumes_service.controller.AlbumController;
import cl.duoc.albumes_service.dto.AlbumCancionDTO;
import cl.duoc.albumes_service.dto.AlbumDTO;
import cl.duoc.albumes_service.dto.UsuarioDTO;
import cl.duoc.albumes_service.model.Album;
import cl.duoc.albumes_service.model.TipoAlbum;
import cl.duoc.albumes_service.service.AlbumService;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlbumController.class)
@DisplayName("Pruebas unitarias para AlbumesController")
public class AlbumesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AlbumService albumService;

    private Album album;
    private Album albumSinID;
    private AlbumDTO albumDTO;
    private UsuarioDTO usuarioDTO;
    private AlbumCancionDTO cancionDTO;
    private TipoAlbum tipoAlbum;
    private List<AlbumCancionDTO> canciones;

    @BeforeEach
    public void setUp() {
        tipoAlbum = new TipoAlbum(
                1L,
                "Sencillo"
        );

        album = new Album(
                1L,
                "Thriller",
                "Tercer disco de Michael Jackson",
                LocalDate.of(1998, 10, 31),
                tipoAlbum,
                1L
        );

        albumSinID = new Album(
                null,
                "Thriller",
                "Tercer disco de Michael Jackson",
                LocalDate.of(1998, 10, 31),
                tipoAlbum,
                1L
        );

        usuarioDTO = new UsuarioDTO(
                1L,
                "Michael Jackson",
                "M. Jackson",
                "m.jackson@gmail.com",
                40,
                1232342334,
                "Artista"
        );

        cancionDTO = new AlbumCancionDTO(
                1L,
                "Bohemian Rhapsody",
                "6:58"
        );

        canciones = Arrays.asList(cancionDTO);

        albumDTO = new AlbumDTO(
                1L,
                "Michael Jackson",
                "Thriller",
                "Tercer disco de Michael Jackson",
                "31/10/1998",
                "Sencillo",
                canciones
        );
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/v1/albumes - Debería retornar 200 OK y la lista de albumes")
    public void findAllAlbumes() throws Exception {

        when(albumService.findAll()).thenReturn(List.of(albumDTO));

        mockMvc.perform(get("/api/v1/albumes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].artista").value("Michael Jackson"))
                .andExpect(jsonPath("$[0].nombre").value("Thriller"))
                .andExpect(jsonPath("$[0].descripcion").value("Tercer disco de Michael Jackson"))
                .andExpect(jsonPath("$[0].fechaLanzamiento").value("31/10/1998"))
                .andExpect(jsonPath("$[0].tipoAlbum").value("Sencillo"))
                .andExpect(jsonPath("$[0].canciones").exists());

    }

    @Test
    @DisplayName("GET /api/v1/albumes/tipo-album - Debería retornar 200 OK y la lista de albumes según su tipo")
    public void findByTipoAlbum() throws Exception {

        Long idTipoAlbum = 1L;
        when(albumService.findAllByTipoAlbum(idTipoAlbum)).thenReturn(List.of(albumDTO));

        mockMvc.perform(get("/api/v1/albumes/tipo-album/" + idTipoAlbum))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].artista").value("Michael Jackson"))
                .andExpect(jsonPath("$[0].nombre").value("Thriller"))
                .andExpect(jsonPath("$[0].descripcion").value("Tercer disco de Michael Jackson"))
                .andExpect(jsonPath("$[0].fechaLanzamiento").value("31/10/1998"))
                .andExpect(jsonPath("$[0].tipoAlbum").value("Sencillo"))
                .andExpect(jsonPath("$[0].canciones").exists());

    }

    @Test
    @DisplayName("GET /api/v1/albumes/between-dates - Debería retornar 200 OK y la lista de albumes según su rango de fecha de lanzamiento")
    public void findAllBetweenDatesAlbumes() throws Exception {

        LocalDate fechaMin = LocalDate.of(1990, 1, 1);
        LocalDate fechaMax = LocalDate.of(2000, 1, 1);
        when(albumService.findAllBetweenDates(fechaMin, fechaMax)).thenReturn(List.of(albumDTO));

        mockMvc.perform(get("/api/v1/albumes/between-dates").param("fecha-min", String.valueOf(fechaMin)).param("fecha-max", String.valueOf(fechaMax)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].artista").value("Michael Jackson"))
                .andExpect(jsonPath("$[0].nombre").value("Thriller"))
                .andExpect(jsonPath("$[0].descripcion").value("Tercer disco de Michael Jackson"))
                .andExpect(jsonPath("$[0].fechaLanzamiento").value("31/10/1998"))
                .andExpect(jsonPath("$[0].tipoAlbum").value("Sencillo"))
                .andExpect(jsonPath("$[0].canciones").exists());

    }

    @Test
    @DisplayName("GET /api/v1/albumes/artista/{idArtista} - Debería retornar 200 OK y la lista de albumes según artista")
    public void findAllByArtistAlbumes() throws Exception {

        Long idArtista = 1L;
        when(albumService.findAllByArtist(idArtista)).thenReturn(List.of(albumDTO));

        mockMvc.perform(get("/api/v1/albumes/artista/" + idArtista))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].artista").value("Michael Jackson"))
                .andExpect(jsonPath("$[0].nombre").value("Thriller"))
                .andExpect(jsonPath("$[0].descripcion").value("Tercer disco de Michael Jackson"))
                .andExpect(jsonPath("$[0].fechaLanzamiento").value("31/10/1998"))
                .andExpect(jsonPath("$[0].tipoAlbum").value("Sencillo"))
                .andExpect(jsonPath("$[0].canciones").exists());

    }

    @Test
    @DisplayName("GET /api/v1/albumes/{idAlbum} - Debería retornar 200 OK y el album según ID")
    public void findByIdAlbum() throws Exception {

        Long idAlbum = 1L;
        when(albumService.findById(idAlbum)).thenReturn(albumDTO);

        mockMvc.perform(get("/api/v1/albumes/" + idAlbum))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.artista").value("Michael Jackson"))
                .andExpect(jsonPath("$.nombre").value("Thriller"))
                .andExpect(jsonPath("$.descripcion").value("Tercer disco de Michael Jackson"))
                .andExpect(jsonPath("$.fechaLanzamiento").value("31/10/1998"))
                .andExpect(jsonPath("$.tipoAlbum").value("Sencillo"))
                .andExpect(jsonPath("$.canciones").exists());

    }

    @Test
    @DisplayName("POST /api/v1/albumes - Debería retornar 201 CREATED y el album creado")
    void postAlbum() throws Exception {

        when(albumService.save(any(Album.class))).thenReturn(album);

        mockMvc.perform(post("/api/v1/albumes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(albumSinID)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Thriller"))
                .andExpect(jsonPath("$.descripcion").value("Tercer disco de Michael Jackson"))
                .andExpect(jsonPath("$.fechaLanzamiento").value("1998-10-31"))
                .andExpect(jsonPath("$.tipoAlbum").exists())
                .andExpect(jsonPath("$.artista").value(1));
    }

    @Test
    @DisplayName("DELETE /api/v1/albumes/{id} - Debería retornar 204 NO CONTENT")
    public void deleteAlbum() throws Exception {

        Long idAlbum = 1L;
        when(albumService.findById(idAlbum)).thenReturn(albumDTO);

        mockMvc.perform(delete("/api/v1/albumes/" + idAlbum))
                .andExpect(status().isNoContent());
    }

}
