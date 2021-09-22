package com.music.app.balance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MusicServicioBalanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicServicioBalanceApplication.class, args);
	}

}
