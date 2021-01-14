package com.noteoline.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
/*@EnableGlobalMethodSecurity(prePostEnabled = true)*/
public class Noteolinev2Application {

	public static void main(String[] args) {
		SpringApplication.run(Noteolinev2Application.class, args);
	}

}
