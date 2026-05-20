package cl.duoc.reproducciones_service.mapper;

import cl.duoc.reproducciones_service.model.Reproduccion;
import cl.duoc.reproducciones_service.dto.ReproduccionDTO;
import cl.duoc.reproducciones_service.dto.CancionDTO;
import cl.duoc.reproducciones_service.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Component
public class ReproduccionMapper {

    public ReproduccionDTO toDTO(Reproduccion r, CancionDTO c, UsuarioDTO u) {
        if ( r == null ) {
            return null;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
        ReproduccionDTO dto = new ReproduccionDTO();
        int minutos = r.getSegundosEscuchados() / 60;
        int segundos = r.getSegundosEscuchados() % 60;

        dto.setId( r.getId() );
        dto.setFecha_reproduccion(r.getFechaReproduccion().format(dtf));

        if ( segundos < 10 ) {
            dto.setTiempo_escuchado( minutos + ":0" + segundos );

        } else {
            dto.setTiempo_escuchado( minutos + ":" + segundos );
        }

        if ( r.getDispositivo() != null ) {
            dto.setDispositivo( r.getDispositivo().getNombre() );

        } else {
            dto.setDispositivo( "Sin Dispositivo" );
        }

        dto.setUsuario( u.getEmail() );
        dto.setCancion( c );
        return dto;

    }

}
