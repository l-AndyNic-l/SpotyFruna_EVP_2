package cl.duoc.reproducciones_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Reproducciones-Service")
                        .version("1.0.0")
                        .description("""
						API ENCARGADA DE GESTIONAR REPRODUCCIONES/DISPOSITIVOS.
						
						Funcionalidades principales:
						- CRUD (findAll, findById, save, update y delete).
						- Busquedas personalizadas como: findAllBy [Dispositivo, Cancion, Usuario] y findAllBetween [Dates].
						
						Equipo responsable:
						- Adrían Galo Lopez        - adri.lopez@duocuc.cl
						- Benjamín Albornoz Caces  - be.albornozc@duocuc.cl
						- Ignacia Vasquez Erices   - ignac.vasquez@duocuc.cl
						""")
                        .contact(new Contact()
                                .name("Consultas/Soporte")
                                .email("adri.lopez@duocuc.cl")
                        )
                );
    }

}
