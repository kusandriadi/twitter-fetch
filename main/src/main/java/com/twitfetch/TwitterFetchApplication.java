package com.hasanpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HasanPosApplication {

	public static void main(String[] args) {
		SpringApplication.run(HasanPosApplication.class, args);
	}

}
