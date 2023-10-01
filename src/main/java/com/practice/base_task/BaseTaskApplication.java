package com.practice.base_task;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.practice.base_task.*"})
@EnableJpaRepositories
@OpenAPIDefinition
@SpringBootApplication
public class BaseTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseTaskApplication.class, args);
	}

}
