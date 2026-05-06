package cl.duoc.playlists_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PlaylistsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistsServiceApplication.class, args);
	}

}
