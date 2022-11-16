package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AlphaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlphaTestApplication.class, args);
	}

}
