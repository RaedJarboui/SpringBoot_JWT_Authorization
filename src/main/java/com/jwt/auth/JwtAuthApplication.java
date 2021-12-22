package com.jwt.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.jwt.auth.utils", "com.jwt.auth.service", "com.jwt.auth.security.jwt",
		"com.jwt.auth.security"})
@EntityScan("com.jwt.auth.models")
@EnableJpaRepositories("com.jwt.auth.repository")
public class JwtAuthApplication {

	public static void main(String[] args) {

		SpringApplication.run(JwtAuthApplication.class, args);
	}

}
