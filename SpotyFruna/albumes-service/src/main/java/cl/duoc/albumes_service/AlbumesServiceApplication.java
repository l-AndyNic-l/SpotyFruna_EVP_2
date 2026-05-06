package cl.duoc.albumes_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AlbumesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumesServiceApplication.class, args);
	}

}
