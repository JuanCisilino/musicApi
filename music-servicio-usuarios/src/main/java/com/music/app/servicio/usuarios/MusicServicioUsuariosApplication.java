package com.music.app.servicio.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MusicServicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicServicioUsuariosApplication.class, args);
	}

}
