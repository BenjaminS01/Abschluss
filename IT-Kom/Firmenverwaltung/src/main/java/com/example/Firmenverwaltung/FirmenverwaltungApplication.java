package com.example.Firmenverwaltung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FirmenverwaltungApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirmenverwaltungApplication.class, args);
	}

}
