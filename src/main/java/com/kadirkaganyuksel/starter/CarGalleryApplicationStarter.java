package com.kadirkaganyuksel.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@ComponentScan(basePackages = {"com.kadirkaganyuksel"})
@EntityScan(basePackages = {"com.kadirkaganyuksel"})
@EnableJpaRepositories(basePackages = {"com.kadirkaganyuksel"})
@SpringBootApplication
public class CarGalleryApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(CarGalleryApplicationStarter.class, args);
	}

}
