package com.technicalyorker.dbs.gorden;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.technicalyorker.dbs.gorden.service.EateryService;

@SpringBootApplication
public class DbsApplication {
	private static final Logger logger = LoggerFactory.getLogger(DbsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DbsApplication.class, args);
	}

	@Autowired
	EateryService eatery;

	@Bean
	public CommandLineRunner run() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				logger.debug("Application Bootstrapped!");
			}
		};
	}
}
