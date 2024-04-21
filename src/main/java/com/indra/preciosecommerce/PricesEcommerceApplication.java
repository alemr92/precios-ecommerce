package com.indra.preciosecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.indra.*"})
@ComponentScan("com.indra.*")
public class PricesEcommerceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PricesEcommerceApplication.class, args);
	}
}
