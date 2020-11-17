package io.libreria.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibreriaController {
	
	@GetMapping("/")
	public String Homepage() {
		return "---Welcome to Libreria---";
	}
}
