package com.gd.clinic;

import com.gd.clinic.config.security.entity.User;
import com.gd.clinic.config.security.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class
SpringTasksApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringTasksApplication.class, args);
	}
	@Bean
	CommandLineRunner run(UserRepo repo){
		return args -> {repo.save(new User("Arturo", "Olvera", "aolvera", "$2a$12$yccM2.hINPSDFmHkRatOKu3bY2ayZD3c33bs2jxkOm2r6qHQ7waS2"));};
	}
}
