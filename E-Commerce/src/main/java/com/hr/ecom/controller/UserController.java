package com.hr.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.ecom.model.User;
import com.hr.ecom.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping("/api/users")
	public List<User> getAllUsers() {
		return userService.fethAllUsers();
	}
	
	@PostMapping("/api/users")
	public String createUser(@RequestBody User user) {
		userService.createUser(user);
		return "user added successfully";
	}
	
}
