package com.example.services;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class HotelServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceAppApplication.class, args);
	}
}
