package cl.duoc.usuarios_service;

import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.mapper.UsuarioMapper;
import cl.duoc.usuarios_service.model.TipoUsuario;
import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.repository.TipoUsuarioRepository;
import cl.duoc.usuarios_service.repository.UsuarioRepository;
import cl.duoc.usuarios_service.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para UsuariosService")
public class UsuariosServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
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

    @Test
    @DisplayName("Debe listar todos los usuarios correctamente")
    public void findAllUsuarios() {

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        List<UsuarioDTO> resultado = usuarioService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("freddy.queen@gmail.com", resultado.get(0).getEmail());

        verify(usuarioRepository).findAll();
        verify(usuarioMapper).toDTO(usuario);
    }

    @Test
    @DisplayName("Debe buscar a un usuario por el ID correctamente")
    public void findByIdUsuario() {

        Long idUsuario = 1L;
        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.ofNullable(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        UsuarioDTO resultado = usuarioService.findById(idUsuario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Freddy Mercury", resultado.getNombreCompleto());

        verify(usuarioRepository).findById(idUsuario);
        verify(usuarioMapper).toDTO(usuario);
    }

    @Test
    @DisplayName("Debe listar todos los usuarios por el tipo de usuario correctamente")
    public void findAllTipoUsuario() {

        Long idTipo = 1L;
        usuario.setTipoUsuario(tipoUsuario);

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        when(tipoUsuarioRepository.existsById(idTipo)).thenReturn(true);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        List<UsuarioDTO> resultado = usuarioService.findAllByTipoUsuario(idTipo);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("freddy.queen@gmail.com", resultado.get(0).getEmail());

        verify(usuarioRepository).findAll();
        verify(tipoUsuarioRepository).existsById(idTipo);
        verify(usuarioMapper).toDTO(usuario);
    }

    @Test
    @DisplayName("Debe buscar a un usuario por el nickname correctamente")
    public void findByNicknameUsuario() {

        String nickname = "Queen";
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        List<UsuarioDTO> resultado = usuarioService.findAllByNickname(nickname);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("freddy.queen@gmail.com", resultado.get(0).getEmail());

        verify(usuarioRepository).findAll();
        verify(usuarioMapper).toDTO(usuario);
    }

    @Test
    @DisplayName("Debe listar todos los usuarios por el rango de fecha de nacimiento correctamente")
    public void findAllBetweenDatesUsuario() {

        LocalDate fechaMin = LocalDate.of(1965, 1, 1);
        LocalDate fechaMax = LocalDate.of(1975, 1, 1);

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        List<UsuarioDTO> resultado = usuarioService.findAllBetweenDates(fechaMin, fechaMax);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("freddy.queen@gmail.com", resultado.get(0).getEmail());

        verify(usuarioRepository).findAll();
        verify(usuarioMapper).toDTO(usuario);
    }

    @Test
    @DisplayName("Debe guardar un usuario correctamente")
    public void saveUsuario() {

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.save(usuario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("freddy.queen@gmail.com", resultado.getEmail());

        verify(usuarioRepository).save(usuario);
    }

    @Test
    @DisplayName("Debe eliminar un usuario por ID correctamente")
    public void deleteUsuario() {

        Long idUsuario = 1L;
        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.ofNullable(usuario));

        usuarioService.deleteById(1L);

        verify(usuarioRepository).deleteById(1L);
        verify(usuarioRepository).findById(idUsuario);
    }

}
