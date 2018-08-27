package com.personal.replenish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableAutoConfiguration

public class ReplenishtaskApplication {
	private static final Logger logger = LoggerFactory.getLogger(ReplenishtaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReplenishtaskApplication.class, args);
	}
	
	

}
