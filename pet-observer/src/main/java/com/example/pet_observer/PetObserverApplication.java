package com.example.pet_observer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PetObserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetObserverApplication.class, args);
	}

}
