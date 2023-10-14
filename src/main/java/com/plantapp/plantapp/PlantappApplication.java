package com.plantapp.plantapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.plantapp.plantapp.plant")
@EnableJpaRepositories(basePackages = "com.plantapp.plantapp.plant")
public class PlantappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantappApplication.class, args);
	}

}
