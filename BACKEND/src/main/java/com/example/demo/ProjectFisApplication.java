package com.example.demo;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@OpenAPIDefinition(info = @Info(title = "Bookworms APIs", version = "1.0.0", description = "Library Management"))
public class ProjectFisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectFisApplication.class, args);
	}

}
