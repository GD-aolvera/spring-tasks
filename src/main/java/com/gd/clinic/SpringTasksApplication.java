package com.gd.clinic;

import com.gd.clinic.entities.Patient;
import com.gd.clinic.model.PatientResponseDto;
import com.gd.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}

