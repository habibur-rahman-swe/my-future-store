package com.hr.ecom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hr.ecom.model.User;

@Service
public class UserService {
	
	private List<User> userList = new ArrayList<>();
	
	public List<User> fethAllUsers() {
		return userList;
	}
	
	public List<User> createUser(User user) {
		userList.add(user);
		return userList;
	}
}
