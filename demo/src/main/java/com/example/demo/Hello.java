package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello { 
	@GetMapping("/")
	public String controller() {
		return "hello  this is my first spring boot";
	}
}
