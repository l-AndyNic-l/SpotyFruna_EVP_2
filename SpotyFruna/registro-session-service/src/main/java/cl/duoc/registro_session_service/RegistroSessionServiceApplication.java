package cl.duoc.registro_session_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RegistroSessionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistroSessionServiceApplication.class, args);
	}

}
