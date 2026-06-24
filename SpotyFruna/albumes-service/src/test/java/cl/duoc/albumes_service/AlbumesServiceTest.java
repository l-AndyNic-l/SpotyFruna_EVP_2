package cl.duoc.albumes_service;

import cl.duoc.albumes_service.clients.CancionClient;
import cl.duoc.albumes_service.clients.UsuarioClient;
import cl.duoc.albumes_service.dto.AlbumCancionDTO;
import cl.duoc.albumes_service.dto.AlbumDTO;
import cl.duoc.albumes_service.dto.UsuarioDTO;
import cl.duoc.albumes_service.mapper.AlbumMapper;
import cl.duoc.albumes_service.model.Album;
import cl.duoc.albumes_service.model.TipoAlbum;
import cl.duoc.albumes_service.repository.AlbumRepository;
import cl.duoc.albumes_service.repository.TipoAlbumRepository;
import cl.duoc.albumes_service.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para AlbumesService")
public class AlbumesServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private AlbumMapper albumMapper;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private CancionClient cancionClient;

    @Mock
    private TipoAlbumRepository tipoAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

    private Album album;
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

    @Test
    @DisplayName("Debe listar todos los albumes correctamente")
    public void findAllAlbumes() {

        when(albumRepository.findAll()).thenReturn(List.of(album));
        when(usuarioClient.findById(album.getArtista())).thenReturn(usuarioDTO);
        when(cancionClient.findAllByIdAlbum(album.getId())).thenReturn(List.of(cancionDTO));
        when(albumMapper.toDTO(album, usuarioDTO, canciones)).thenReturn(albumDTO);

        List<AlbumDTO> resultado = albumService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());

        verify(albumRepository).findAll();
        verify(usuarioClient).findById(album.getArtista());
        verify(cancionClient).findAllByIdAlbum(album.getId());
        verify(albumMapper).toDTO(album, usuarioDTO, canciones);
    }

    @Test
    @DisplayName("Debe buscar a un album por el ID correctamente")
    public void findByIdAlbum() {

        Long idAlbum = 1L;
        when(albumRepository.findById(idAlbum)).thenReturn(Optional.ofNullable(album));
        when(usuarioClient.findById(album.getArtista())).thenReturn(usuarioDTO);
        when(cancionClient.findAllByIdAlbum(album.getId())).thenReturn(List.of(cancionDTO));
        when(albumMapper.toDTO(album, usuarioDTO, canciones)).thenReturn(albumDTO);

        AlbumDTO resultado = albumService.findById(idAlbum);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Thriller", resultado.getNombre());

        verify(albumRepository).findById(idAlbum);
        verify(usuarioClient).findById(album.getArtista());
        verify(cancionClient).findAllByIdAlbum(album.getId());
        verify(albumMapper).toDTO(album, usuarioDTO, canciones);
    }

    @Test
    @DisplayName("Debe listar todos los albumes por el tipo de album correctamente")
    public void findAllTipoAlbum() {

        Long idTipoAlbum = 1L;
        album.setTipoAlbum(tipoAlbum);

        when(tipoAlbumRepository.existsById(idTipoAlbum)).thenReturn(true);
        when(albumRepository.findAll()).thenReturn(List.of(album));
        when(usuarioClient.findById(album.getArtista())).thenReturn(usuarioDTO);
        when(cancionClient.findAllByIdAlbum(album.getId())).thenReturn(List.of(cancionDTO));
        when(albumMapper.toDTO(album, usuarioDTO, canciones)).thenReturn(albumDTO);

        List<AlbumDTO> resultado = albumService.findAllByTipoAlbum(idTipoAlbum);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Thriller", resultado.get(0).getNombre());

        verify(tipoAlbumRepository).existsById(idTipoAlbum);
        verify(albumRepository).findAll();
        verify(usuarioClient).findById(album.getArtista());
        verify(cancionClient).findAllByIdAlbum(album.getId());
        verify(albumMapper).toDTO(album, usuarioDTO, canciones);
    }

    @Test
    @DisplayName("Debe listar todos los albumes por el artista correctamente")
    public void findAllByArtistAlbum() {
        Long idArtista = 1L;

        when(usuarioClient.findById(idArtista)).thenReturn(usuarioDTO);
        when(albumRepository.findAll()).thenReturn(List.of(album));
        when(cancionClient.findAllByIdAlbum(album.getId())).thenReturn(List.of(cancionDTO));
        when(albumMapper.toDTO(album, usuarioDTO, canciones)).thenReturn(albumDTO);

        List<AlbumDTO> resultado = albumService.findAllByArtist(idArtista);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Thriller", resultado.get(0).getNombre());

        verify(albumRepository).findAll();
        verify(usuarioClient, times(2)).findById(idArtista);
        verify(cancionClient).findAllByIdAlbum(album.getId());
        verify(albumMapper).toDTO(album, usuarioDTO, canciones);
    }

    @Test
    @DisplayName("Debe listar todos los albumes por el rango de fecha de lanzamiento correctamente")
    public void findAllBetweenDatesAlbumes() {

        LocalDate fechaMin = LocalDate.of(1990, 1, 1);
        LocalDate fechaMax = LocalDate.of(2000, 1, 1);

        when(albumRepository.findAll()).thenReturn(List.of(album));
        when(usuarioClient.findById(album.getArtista())).thenReturn(usuarioDTO);
        when(cancionClient.findAllByIdAlbum(album.getId())).thenReturn(List.of(cancionDTO));
        when(albumMapper.toDTO(album, usuarioDTO, canciones)).thenReturn(albumDTO);

        List<AlbumDTO> resultado = albumService.findAllBetweenDates(fechaMin, fechaMax);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Thriller", resultado.get(0).getNombre());

        verify(albumRepository).findAll();
        verify(usuarioClient).findById(album.getArtista());
        verify(cancionClient).findAllByIdAlbum(album.getId());
        verify(albumMapper).toDTO(album, usuarioDTO, canciones);
    }

    @Test
    @DisplayName("Debe guardar un album correctamente")
    public void saveAlbum() {

        when(albumRepository.save(album)).thenReturn(album);

        Album resultado = albumService.save(album);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Thriller", resultado.getNombre());

        verify(albumRepository).save(album);
    }

    @Test
    @DisplayName("Debe eliminar un album por ID correctamente")
    public void deleteAlbum() {

        Long idAlbum = 1L;
        when(albumRepository.findById(idAlbum)).thenReturn(Optional.ofNullable(album));

        albumService.deleteById(1L);

        verify(albumRepository).deleteById(1L);
        verify(albumRepository).findById(idAlbum);
    }

}