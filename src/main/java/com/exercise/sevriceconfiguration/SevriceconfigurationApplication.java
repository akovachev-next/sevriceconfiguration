package com.exercise.sevriceconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SevriceconfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SevriceconfigurationApplication.class, args);
	}

}
