package com.gd.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@EnableConfigurationProperties(GeneralConfiguration.class)
public class
SpringTasksApplication {

	public static void main(String[] args) {
		//ConfigurableApplicationContext c =
		SpringApplication.run(SpringTasksApplication.class, args);
		//AppConfiguration g=c.getBean( AppConfiguration.class);
		//String d=g.getAccessTokenSecret();
	//	System.out.println("----------------------"+d);


	}

}
