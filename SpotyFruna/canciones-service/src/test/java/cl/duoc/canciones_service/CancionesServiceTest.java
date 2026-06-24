package cl.duoc.canciones_service;

import cl.duoc.canciones_service.clients.AlbumClient;
import cl.duoc.canciones_service.dto.AlbumCancionDTO;
import cl.duoc.canciones_service.dto.AlbumDTO;
import cl.duoc.canciones_service.dto.CancionDTO;
import cl.duoc.canciones_service.mapper.AlbumCancionMapper;
import cl.duoc.canciones_service.mapper.CancionMapper;
import cl.duoc.canciones_service.model.Cancion;
import cl.duoc.canciones_service.model.Genero;
import cl.duoc.canciones_service.repository.CancionRepository;
import cl.duoc.canciones_service.repository.GeneroRepository;
import cl.duoc.canciones_service.service.CancionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para CancionesService")
public class CancionesServiceTest {

    @Mock
    private CancionRepository cancionRepository;

    @Mock
    private CancionMapper cancionMapper;

    @Mock
    private AlbumClient albumClient;

    @Mock
    private AlbumCancionMapper albumMapper;

    @Mock
    private GeneroRepository  generoRepository;

    @InjectMocks
    private CancionService cancionService;

    private Cancion cancion;
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

    @Test
    @DisplayName("Debe listar todas las canciones correctamente")
    public void findAllCanciones() {

        when(cancionRepository.findAll()).thenReturn(List.of(cancion));
        when(albumClient.findById(cancion.getIdAlbum())).thenReturn(albumDTO);
        when(cancionMapper.toDTO(cancion, albumDTO)).thenReturn(cancionDTO);

        List<CancionDTO> resultado = cancionService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Bohemian Rhapsody", resultado.get(0).getTitulo());

        verify(cancionRepository).findAll();
        verify(albumClient).findById(cancion.getIdAlbum());
        verify(cancionMapper).toDTO(cancion, albumDTO);
    }

    @Test
    @DisplayName("Debe buscar una cancion por el ID correctamente")
    public void findByIdCancion() {

        Long idCancion = 1L;
        when(cancionRepository.findById(idCancion)).thenReturn(Optional.ofNullable(cancion));
        when(albumClient.findById(cancion.getIdAlbum())).thenReturn(albumDTO);
        when(cancionMapper.toDTO(cancion, albumDTO)).thenReturn(cancionDTO);

        CancionDTO resultado = cancionService.findById(idCancion);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Bohemian Rhapsody", resultado.getTitulo());

        verify(cancionRepository).findById(idCancion);
        verify(albumClient).findById(cancion.getIdAlbum());
        verify(cancionMapper).toDTO(cancion, albumDTO);
    }

    @Test
    @DisplayName("Debe listar todas las canciones por el album correctamente")
    public void findAllByIdAlbumCanciones() {

        Long idAlbum = 8L;
        when(cancionRepository.findAllByIdAlbum(idAlbum)).thenReturn(List.of(cancion));
        when(albumMapper.toDTO(cancion)).thenReturn(albumCancionDTO);

        List<AlbumCancionDTO> resultado = cancionService.findAllByIdAlbum(idAlbum);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Bohemian Rhapsody", resultado.get(0).getTitulo());

        verify(cancionRepository).findAllByIdAlbum(idAlbum);
        verify(albumMapper).toDTO(cancion);
    }

    @Test
    @DisplayName("Debe listar todas las canciones por el autor correctamente")
    public void findAllByAutorCanciones() {

        String autor = "Queen";
        when(cancionRepository.existsByAutor(autor)).thenReturn(true);
        when(cancionRepository.findAll()).thenReturn(List.of(cancion));
        when(albumClient.findById(cancion.getIdAlbum())).thenReturn(albumDTO);
        when(cancionMapper.toDTO(cancion, albumDTO)).thenReturn(cancionDTO);

        List<CancionDTO> resultado = cancionService.findAllByAutor(autor);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Bohemian Rhapsody", resultado.get(0).getTitulo());

        verify(cancionRepository).existsByAutor(autor);
        verify(cancionRepository).findAll();
        verify(albumClient).findById(cancion.getIdAlbum());
        verify(cancionMapper).toDTO(cancion, albumDTO);
    }

    @Test
    @DisplayName("Debe guardar una cancion correctamente")
    public void saveCancion() {

        when(cancionRepository.save(cancion)).thenReturn(cancion);

        Cancion resultado = cancionService.save(cancion);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Bohemian Rhapsody", resultado.getTitulo());

        verify(cancionRepository).save(cancion);
    }

    @Test
    @DisplayName("Debe eliminar una cancion por ID correctamente")
    public void deleteCancion() {

        Long idCancion = 1L;
        when(cancionRepository.findById(idCancion)).thenReturn(Optional.ofNullable(cancion));

        cancionService.deleteById(1L);

        verify(cancionRepository).deleteById(1L);
        verify(cancionRepository).findById(idCancion);
    }

    @Test
    @DisplayName("Debe listar todas las canciones por el genero correctamente")
    public void findAllByGeneroCanciones() {

        Long idGenero = 1L;
        cancion.setGenero(genero);

        when(generoRepository.existsById(idGenero)).thenReturn(true);
        when(cancionRepository.findAll()).thenReturn(List.of(cancion));
        when(albumClient.findById(cancion.getIdAlbum())).thenReturn(albumDTO);
        when(cancionMapper.toDTO(cancion, albumDTO)).thenReturn(cancionDTO);

        List<CancionDTO> resultado = cancionService.findAllByGenero(idGenero);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Bohemian Rhapsody", resultado.get(0).getTitulo());

        verify(generoRepository).existsById(idGenero);
        verify(cancionRepository).findAll();
        verify(albumClient).findById(cancion.getIdAlbum());
        verify(cancionMapper).toDTO(cancion, albumDTO);
    }

    @Test
    @DisplayName("Debe listar todos las canciones por el rango de fecha de lanzamiento correctamente")
    public void findAllBetweenDatesCanciones() {

        LocalDate fechaMin = LocalDate.of(1970, 1, 1);
        LocalDate fechaMax = LocalDate.of(1980, 1, 1);

        when(cancionRepository.findAll()).thenReturn(List.of(cancion));
        when(albumClient.findById(cancion.getIdAlbum())).thenReturn(albumDTO);
        when(cancionMapper.toDTO(cancion, albumDTO)).thenReturn(cancionDTO);

        List<CancionDTO> resultado = cancionService.findAllBetweenDates(fechaMin, fechaMax);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());

        verify(cancionRepository).findAll();
        verify(albumClient).findById(cancion.getIdAlbum());
        verify(cancionMapper).toDTO(cancion, albumDTO);
    }

    @Test
    @DisplayName("Debe listar todos las canciones por el rango de duracion correctamente")
    public void findAllBetweenDurationCanciones() {

        Long duracioMin = 1L;
        Long duracionMax = 500L;

        when(cancionRepository.findAll()).thenReturn(List.of(cancion));
        when(albumClient.findById(cancion.getIdAlbum())).thenReturn(albumDTO);
        when(cancionMapper.toDTO(cancion, albumDTO)).thenReturn(cancionDTO);

        List<CancionDTO> resultado = cancionService.findAllBetweenDuration(duracioMin, duracionMax);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());

        verify(cancionRepository).findAll();
        verify(albumClient).findById(cancion.getIdAlbum());
        verify(cancionMapper).toDTO(cancion, albumDTO);
    }
}