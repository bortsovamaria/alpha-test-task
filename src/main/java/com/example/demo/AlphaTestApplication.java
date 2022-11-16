package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AlphaTestApplication { //implements ApplicationRunner {

//	@Value("${type.path}")
//	private String path;

	public static void main(String[] args) {
		SpringApplication.run(AlphaTestApplication.class, args);
	}

//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		System.out.println("---------------------------------");
//		System.out.println("Path: " + path);
//		System.out.println("---------------------------------");
//	}
}
