package com.nn.snaplife.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nn.snaplife.beans.User;
import com.nn.snaplife.repositories.UserRepository;

@RestController
public class UserController {

	private UserRepository userRepository;
	
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("users")
	public List<User> users() {
		List<User> users = userRepository.findAll();
		return users;
	}
}
