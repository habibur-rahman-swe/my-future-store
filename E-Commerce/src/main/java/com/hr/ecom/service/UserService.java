package com.hr.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hr.ecom.dto.AddressDTO;
import com.hr.ecom.dto.UserRequest;
import com.hr.ecom.dto.UserResponse;
import com.hr.ecom.model.Address;
import com.hr.ecom.model.User;
import com.hr.ecom.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public List<UserResponse> fethAllUsers() {
		return userRepository.findAll().stream().map(this::mapToUserResponse).toList();
	}

	public void createUser(UserRequest userRequest) {
		User user = new User();

		updateUserFromRequest(user, userRequest);

		userRepository.save(user);
	}

	public Optional<UserResponse> fetchUser(Long id) {
		return userRepository.findById(id).map(this::mapToUserResponse);
	}

	public boolean updateUser(Long id, UserRequest updateUser) {
		return userRepository.findById(id).map(user -> {
			updateUserFromRequest(user, updateUser);
			userRepository.save(user);
			return true;
		}).orElse(false);
	}

	private UserResponse mapToUserResponse(User user) {
		UserResponse response = new UserResponse();
		response.setId(String.valueOf(user.getId()));
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setEmail(user.getEmail());
		response.setPhone(user.getPhone());
		response.setRole(user.getRole());

		if (user.getAddress() != null) {
			AddressDTO addressDto = new AddressDTO();
			addressDto.setStreet(user.getAddress().getStreet());
			addressDto.setCity(user.getAddress().getCity());
			addressDto.setState(user.getAddress().getState());
			addressDto.setCountry(user.getAddress().getCountry());
			addressDto.setZipcode(user.getAddress().getZipcode());

			response.setAddress(addressDto);
		}

		return response;
	}

	private void updateUserFromRequest(User user, UserRequest userRequest) {
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setEmail(userRequest.getEmail());
		user.setPhone(userRequest.getPhone());
		
		if (userRequest.getAddress() != null) {
			Address address = new Address();
			
			address.setState(userRequest.getAddress().getState());
			address.setCity(userRequest.getAddress().getCity());
			address.setCountry(userRequest.getAddress().getCountry());
			address.setStreet(userRequest.getAddress().getStreet());
			address.setZipcode(userRequest.getAddress().getZipcode());
			
			user.setAddress(address);
		}
	}
}
