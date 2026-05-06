package cl.duoc.auth_service.repository;

import cl.duoc.auth_service.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {

    List<Auth> findAllByUsuario(Long id);

    @Query(value = "select * from auth where to_char(fecha_hora, 'yyyy') = :anio", nativeQuery = true)
    List<Auth> findAllByAnio(int anio);

    @Query(value = "select * from auth where to_char(fecha_hora, 'mm') = :mes and to_char(fecha_hora, 'yyyy') = :anio", nativeQuery = true)
    List<Auth> findAllByMes(int mes, int anio);

    @Query(value = "select * from auth where to_char(fecha_hora, 'dd') = :dia and to_char(fecha_hora, 'mm') = :mes and to_char(fecha_hora, 'yyyy') = :anio", nativeQuery = true)
    List<Auth> findAllByDia(int dia, int mes, int anio);

    @Query(value = "select * from auth where fecha_hora >= to_date(:fecha_ini, 'yyyy-mm-dd') and fecha_hora < to_date(:fecha_ter, 'yyyy-mm-dd')", nativeQuery = true)
    List<Auth> findAllByEntreFechas(String fecha_ini, String fecha_ter);
}
