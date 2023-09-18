package com.practice.base_task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BaseTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseTaskApplication.class, args);
	}

	@Value("${DB_NAME}")
	private String dbName;

	@Value("${DB_USERNAME}")
	private String dbUsername;

	@Value("${DB_PASSWORD}")
	private String dbPassword;

}
