package cl.duoc.auth_service.repository;

import cl.duoc.auth_service.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {

    List<Auth> findAllByUsuario(Long id);

    @Query(value = "select * from auth where year(fecha_registro) = :anio", nativeQuery = true)
    List<Auth> findAllByAnio(int anio);

    @Query(value = "select * from auth where month(fecha_registro) = :mes and year(fecha_registro) = :anio", nativeQuery = true)
    List<Auth> findAllByMes(int mes, int anio);

    @Query(value = "select * from auth where day(fecha_registro) = :dia and month(fecha_registro) = :mes and year(fecha_registro) = :anio", nativeQuery = true)
    List<Auth> findAllByDia(int dia, int mes, int anio);

    @Query(value = "select * from auth where fecha_registro >= :fecha_ini and fecha_registro < :fecha_ter", nativeQuery = true)
    List<Auth> findAllByEntreFechas(String fecha_ini, String fecha_ter);

}
