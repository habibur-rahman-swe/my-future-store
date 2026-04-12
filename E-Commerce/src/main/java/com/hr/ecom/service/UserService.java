package com.hr.ecom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hr.ecom.model.User;

@Service
public class UserService {
	
	private List<User> userList = new ArrayList<>();
	private Long idCounter = 1L;
	
	public List<User> fethAllUsers() {
		return userList;
	}
	
	public List<User> createUser(User user) {
		user.setId(idCounter++);
		userList.add(user);
		return userList;
	}

	public Optional<User> fetchUser(Long id) {
		return userList.stream().filter(user -> user.getId().equals(id)).findFirst();
	}
	
	public boolean updateUser(Long id, User updateUser) {
		return userList.stream()
				.filter(user -> user.getId().equals(id))
				.findFirst()
				.map(existingUser -> {
					existingUser.setFirstName(updateUser.getFirstName());
					existingUser.setLastName(updateUser.getLastName());
					return true;
				}).orElse(false);
	}
}
