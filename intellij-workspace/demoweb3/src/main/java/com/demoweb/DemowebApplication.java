package com.demoweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan(basePackages = { "com.demoweb.entity" })
@SpringBootApplication // @Configuration, @ComponentScan
@EnableScheduling
public class DemowebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemowebApplication.class, args);
	}

}
