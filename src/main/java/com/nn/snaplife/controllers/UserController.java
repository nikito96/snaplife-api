package com.nn.snaplife.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nn.snaplife.beans.LoginResponse;
import com.nn.snaplife.beans.User;
import com.nn.snaplife.beans.UserService;
import com.nn.snaplife.exceptions.BadCredentialsException;

@RestController
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user)
			throws Exception {
		User registeredUser = userService.register(user);
		
		return ResponseEntity.ok(registeredUser);
	}
	
	@PostMapping("/appLogin")
	public ResponseEntity<LoginResponse> login(HttpServletRequest req, @RequestBody User user)
			throws Exception {
		User loggedUser = this.userService.login(req, user);
		
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setSuccess(true);
		loginResponse.setMessage("Login successful!");
		loginResponse.setUser(loggedUser);
		
		return ResponseEntity.ok(loginResponse);
	}
}
