package cl.duoc.suscripciones_service;

import cl.duoc.suscripciones_service.clients.UsuarioClient;
import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.dto.UsuarioDTO;
import cl.duoc.suscripciones_service.mapper.SuscripcionMapper;
import cl.duoc.suscripciones_service.model.Plan;
import cl.duoc.suscripciones_service.model.Suscripcion;
import cl.duoc.suscripciones_service.repository.PlanRepository;
import cl.duoc.suscripciones_service.repository.SuscripcionRepository;
import cl.duoc.suscripciones_service.service.SuscripcionService;
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
@DisplayName("Pruebas unitarias para SuscripcionesService")
public class SuscripcionesServiceTest {

    @Mock
    private SuscripcionRepository suscripcionRepository;

    @Mock
    private SuscripcionMapper suscripcionMapper;

    @Mock
    private PlanRepository planRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @InjectMocks
    private SuscripcionService suscripcionService;

    private Suscripcion suscripcion;
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

    @Test
    @DisplayName("Debe listar todas las suscripciones correctamente")
    public void findAllSuscripciones() {

        when(suscripcionRepository.findAll()).thenReturn(List.of(suscripcion));
        when(usuarioClient.findById(suscripcion.getId())).thenReturn(usuarioDTO);
        when(suscripcionMapper.toDTO(suscripcion, usuarioDTO)).thenReturn(suscripcionDTO);

        List<SuscripcionDTO> resultado = suscripcionService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());

        verify(suscripcionRepository).findAll();
        verify(usuarioClient).findById(suscripcion.getId());
        verify(suscripcionMapper).toDTO(suscripcion, usuarioDTO);
    }

    @Test
    @DisplayName("Debe buscar una suscripción por el ID correctamente")
    public void findByIdSuscripcion() {

        Long idSuscripcion = 1L;
        when(suscripcionRepository.findById(idSuscripcion)).thenReturn(Optional.ofNullable(suscripcion));
        when(usuarioClient.findById(suscripcion.getIdUsuario())).thenReturn(usuarioDTO);
        when(suscripcionMapper.toDTO(suscripcion, usuarioDTO)).thenReturn(suscripcionDTO);

        SuscripcionDTO resultado = suscripcionService.findById(idSuscripcion);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Freddy Mercury", resultado.getUsuario());

        verify(suscripcionRepository).findById(idSuscripcion);
        verify(usuarioClient).findById(suscripcion.getIdUsuario());
        verify(suscripcionMapper).toDTO(suscripcion, usuarioDTO);
    }

    @Test
    @DisplayName("Debe listar todas las suscripciones por el rango de fecha de suscripcion correctamente")
    public void findAllBetweenDatesSuscripciones() {

        LocalDate fechaMin = LocalDate.of(2020, 1, 1);
        LocalDate fechaMax = LocalDate.of(2026, 1, 1);

        when(suscripcionRepository.findAll()).thenReturn(List.of(suscripcion));
        when(usuarioClient.findById(suscripcion.getIdUsuario())).thenReturn(usuarioDTO);
        when(suscripcionMapper.toDTO(suscripcion, usuarioDTO)).thenReturn(suscripcionDTO);

        List<SuscripcionDTO> resultado = suscripcionService.findAllBetweenDates(fechaMin, fechaMax);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());

        verify(suscripcionRepository).findAll();
        verify(usuarioClient).findById(suscripcion.getIdUsuario());
        verify(suscripcionMapper).toDTO(suscripcion, usuarioDTO);
    }

    @Test
    @DisplayName("Debe listar todas las suscripciones por actividad correctamente")
    public void findAllByActivado() {

        Boolean activada = false;

        when(suscripcionRepository.findAll()).thenReturn(List.of(suscripcion));
        when(usuarioClient.findById(suscripcion.getIdUsuario())).thenReturn(usuarioDTO);
        when(suscripcionMapper.toDTO(suscripcion, usuarioDTO)).thenReturn(suscripcionDTO);

        List<SuscripcionDTO> resultado = suscripcionService.findAllByActivado(activada);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());

        verify(suscripcionRepository).findAll();
        verify(usuarioClient).findById(suscripcion.getIdUsuario());
        verify(suscripcionMapper).toDTO(suscripcion, usuarioDTO);
    }

    @Test
    @DisplayName("Debe listar todas las suscripciones por el plan correctamente")
    public void findAllByPlanSuscripcion() {

        Long idPlan = 1L;
        suscripcion.setPlan(plan);

        when(suscripcionRepository.findAll()).thenReturn(List.of(suscripcion));
        when(usuarioClient.findById(suscripcion.getIdUsuario())).thenReturn(usuarioDTO);
        when(planRepository.existsById(idPlan)).thenReturn(true);
        when(suscripcionMapper.toDTO(suscripcion, usuarioDTO)).thenReturn(suscripcionDTO);

        List<SuscripcionDTO> resultado = suscripcionService.findAllByPlan(idPlan);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());

        verify(suscripcionRepository).findAll();
        verify(usuarioClient).findById(suscripcion.getIdUsuario());
        verify(planRepository).existsById(idPlan);
        verify(suscripcionMapper).toDTO(suscripcion, usuarioDTO);
    }

    @Test
    @DisplayName("Debe guardar una suscripcion correctamente")
    public void saveSuscripcion() {

        when(suscripcionService.save(suscripcion)).thenReturn(suscripcion);

        Suscripcion resultado = suscripcionService.save(suscripcion);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(1L, resultado.getIdUsuario());

        verify(suscripcionRepository).save(suscripcion);
    }
    @Test
    @DisplayName("Debe eliminar una suscripcion por ID correctamente")
    public void deleteUsuario() {

        Long idSuscripcion = 1L;
        when(suscripcionRepository.findById(idSuscripcion)).thenReturn(Optional.ofNullable(suscripcion));

        suscripcionService.deleteById(1L);

        verify(suscripcionRepository).deleteById(1L);
        verify(suscripcionRepository).findById(idSuscripcion);
    }

}
