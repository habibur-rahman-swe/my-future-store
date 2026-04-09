package com.hr.ecom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@PostMapping("/hello")
	public String helloPost(@RequestBody String name) {
		return "Hello " + name + " from POST";
	}
	
}
