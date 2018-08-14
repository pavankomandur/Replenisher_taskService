package com.personal.replenish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.personal.replenish.entity.Customer;
import com.personal.replenish.repository.CustomerRepository;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class ReplenishtaskApplication {
	private static final Logger logger = LoggerFactory.getLogger(ReplenishtaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReplenishtaskApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));
		
			// fetch all customers
			
			for (Customer customer : repository.findAll()) {
				logger.debug(customer.toString());
			}
			

			// fetch an individual customer by ID
			repository.findById(1L)
				.ifPresent(customer -> {
					logger.debug("Customer found with findById(1L):");
					logger.debug("--------------------------------");
					logger.debug(customer.toString());
					logger.debug("");
				});

			// fetch customers by last name
			logger.debug("Customer found with findByLastName('Bauer'):");
			logger.debug("--------------------------------------------");
			
			repository.findByLastName("Bauer").forEach(bauer -> {
				logger.debug(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// 	log.info(bauer.toString());
			// }
			logger.debug("");
		};
	}

}
