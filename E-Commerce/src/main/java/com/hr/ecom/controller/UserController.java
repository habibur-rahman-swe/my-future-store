package com.hr.ecom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.ecom.dto.UserRequest;
import com.hr.ecom.dto.UserResponse;
import com.hr.ecom.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		return new ResponseEntity<List<UserResponse>>(userService.fethAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
		return userService.fetchUser(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody UserRequest user) {
		userService.createUser(user);
		return ResponseEntity.ok("user added successfully");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest user) {
		Boolean updated = userService.updateUser(id, user);
		if (updated) {
			return ResponseEntity.ok("user updated successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
