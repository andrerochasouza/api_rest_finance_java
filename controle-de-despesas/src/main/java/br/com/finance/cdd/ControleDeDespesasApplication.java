package br.com.finance.cdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.finance.cdd.model.entities"})
@ComponentScan(basePackages = {"br.com.finance.cdd.*"})
@EnableJpaRepositories(basePackages = {"br.com.finance.cdd.model.repository"})
@EnableTransactionManagement
public class ControleDeDespesasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleDeDespesasApplication.class, args);
	}

}
