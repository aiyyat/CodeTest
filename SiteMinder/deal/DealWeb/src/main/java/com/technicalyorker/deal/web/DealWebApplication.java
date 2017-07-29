package com.technicalyorker.deal.web;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableZuulProxy
@ComponentScan
public class DealWebApplication {
	private static final Logger logger = Logger.getLogger(DealWebApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DealWebApplication.class, args);
	}
}
