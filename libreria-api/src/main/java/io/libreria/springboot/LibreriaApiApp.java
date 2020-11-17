package io.libreria.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAuthorizationServer
//@EnableResourceServer
@SpringBootApplication
@EnableJpaRepositories
public class LibreriaApiApp {

	public static void main(String[] args) {
		SpringApplication.run(LibreriaApiApp.class, args);
	}

}
 