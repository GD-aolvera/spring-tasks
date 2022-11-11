package com.gd.clinic;

import com.gd.clinic.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.management.MXBean;

@SpringBootApplication
public class
SpringTasksApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringTasksApplication.class, args);
	}
	@Bean
	CommandLineRunner run(User u){
		return args -> {new User(null, "Arturo", "Olvera", "aolvera", "aolvera");};
	}
}
