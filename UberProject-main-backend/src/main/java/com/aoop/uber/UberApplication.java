package com.aoop.uber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.aoop.uber", "com.aoop.uber.config", "com.aoop.uber.controller", "com.aoop.uber.service", "com.aoop.uber.dao", "com.aoop.uber.model", "com.aoop.uber.service"})

public class UberApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberApplication.class, args);
	}

}
