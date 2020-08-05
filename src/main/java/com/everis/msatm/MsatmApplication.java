package com.everis.msatm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsatmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsatmApplication.class, args);
	}

}
