package com.initaitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties
public class StartingItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartingItemsApplication.class, args);
	}

}
