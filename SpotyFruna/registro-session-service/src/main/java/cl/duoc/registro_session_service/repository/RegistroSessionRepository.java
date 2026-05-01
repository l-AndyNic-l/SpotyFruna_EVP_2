package cl.duoc.registro_session_service.repository;

import cl.duoc.registro_session_service.model.RegistroSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroSessionRepository extends JpaRepository<RegistroSession, Long> {

    List<RegistroSession> findAllByUsuario(Long id);

    @Query(value = "select * from registro_session where to_char(fecha_hora, 'yyyy') = :anio", nativeQuery = true)
    List<RegistroSession> findAllByAnio(int anio);

    @Query(value = "select * from registro_session where to_char(fecha_hora, 'mm') = :mes and to_char(fecha_hora, 'yyyy') = :anio", nativeQuery = true)
    List<RegistroSession> findAllByMes(int mes, int anio);

    @Query(value = "select * from registro_session where to_char(fecha_hora, 'dd') = :dia and to_char(fecha_hora, 'mm') = :mes and to_char(fecha_hora, 'yyyy') = :anio", nativeQuery = true)
    List<RegistroSession> findAllByDia(int dia, int mes, int anio);

    @Query(value = "select * from registro_session where fecha_hora >= to_date(:fecha_ini, 'yyyy-mm-dd') and fecha_hora < to_date(:fecha_ter, 'yyyy-mm-dd')", nativeQuery = true)
    List<RegistroSession> findAllByEntreFechas(String fecha_ini, String fecha_ter);
}
