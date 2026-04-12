package com.hr.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hr.ecom.model.User;
import com.hr.ecom.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public List<User> fethAllUsers() {
		return userRepository.findAll();
	}
	
	public void createUser(User user) {
		userRepository.save(user);
	}

	public Optional<User> fetchUser(Long id) {
		return fethAllUsers().stream().filter(user -> user.getId().equals(id)).findFirst();
	}
	
	public boolean updateUser(Long id, User updateUser) {
		return userRepository.findById(id).map(user -> {
			user.setFirstName(updateUser.getFirstName());
			user.setLastName(updateUser.getLastName());
			user.setEmail(updateUser.getEmail());
			user.setPhone(updateUser.getPhone());

			userRepository.save(user);
			return true;
		}).orElse(false);
	}
}
