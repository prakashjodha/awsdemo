package com.prakash.awsdemo;

import com.prakash.awsdemo.dao.ProductRepository;
import com.prakash.awsdemo.dao.UserRepository;
import com.prakash.awsdemo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDynamoDBRepositories(
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
						ProductRepository.class}
				)}
)
@EnableJpaRepositories(
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
						UserRepository.class}
				)}
)
public class AwsdemoApplication {

	private static final Logger log = LoggerFactory.getLogger(AwsdemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AwsdemoApplication.class, args);
	}


	//@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new User("a1","al1","a1@a.com","test"));
			repository.save(new User("a2","al2","a2@a.com","test"));
			repository.save(new User("a3","al3","a3@a.com","test"));
			repository.save(new User("a4","al4","a4@a.com","test"));
			repository.save(new User("a5","al5","a5@a.com","test"));
			repository.save(new User("a1","al1","a1@a.com","test"));
			repository.save(new User("a2","al2","a2@a.com","test"));
			repository.save(new User("a3","al3","a3@a.com","test"));
			repository.save(new User("a4","al4","a4@a.com","test"));
			repository.save(new User("a5","al5","a5@a.com","test"));

			// fetch all customers
			log.info("User found with findAll():");
			log.info("-------------------------------");
			for (User user : repository.findAll()) {
				log.info(user.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			User user = repository.findById(1L).get();
			log.info("User found with findById(1L):");
			log.info("--------------------------------");
			log.info(user.toString());
			log.info("");

			// fetch customers by last name
			log.info("User found with findByLastName('a1'):");
			log.info("--------------------------------------------");
			repository.findByFirstName("a1").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");

		};
	}



}
