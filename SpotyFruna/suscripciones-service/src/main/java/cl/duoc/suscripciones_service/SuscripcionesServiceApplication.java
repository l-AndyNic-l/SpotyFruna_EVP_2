package cl.duoc.suscripciones_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SuscripcionesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuscripcionesServiceApplication.class, args);
    }

}
