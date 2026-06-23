package cl.duoc.canciones_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerClass {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Canciones-Service")
                        .version("1.0.0")
                        .description("""
						API ENCARGADA DE GESTIONAR CANCIONES/GÉNEROS.
						
						Funcionalidades principales:
						- CRUD (findAll, findById, save, update y delete).
						- Busquedas personalizadas: findAllBy [Album, Autor/Artista, Genero] y findAllBetween [Dates, Duration].
						
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
