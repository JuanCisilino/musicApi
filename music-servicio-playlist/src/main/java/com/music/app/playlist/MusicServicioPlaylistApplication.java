package com.music.app.playlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MusicServicioPlaylistApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicServicioPlaylistApplication.class, args);
	}

}
