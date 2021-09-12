package com.example.Besucher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BesucherApplication {

	public static void main(String[] args) {
		SpringApplication.run(BesucherApplication.class, args);
	}

}
