package com.music.app.track;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MusicServicioTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicServicioTrackApplication.class, args);
	}

}
