package com.nn.snaplife.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nn.snaplife.beans.User;
import com.nn.snaplife.beans.UserService;
import com.nn.snaplife.beans.responses.UserResponse;
import com.nn.snaplife.beans.responses.Response;

@RestController
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody User user)
			throws Exception {
		User registeredUser = userService.register(user);
		
		UserResponse registrationResponse = new UserResponse();
		registrationResponse.setSuccess(true);
		registrationResponse.setMessage("Registration successful!");
		registrationResponse.setUser(registeredUser);
		
		return ResponseEntity.ok(registrationResponse);
	}
	
	@PostMapping("/appLogin")
	public ResponseEntity<Response> login(HttpServletRequest req, @RequestBody User user)
			throws Exception {
		User loggedUser = this.userService.login(req, user);
		
		UserResponse loginResponse = new UserResponse();
		loginResponse.setSuccess(true);
		loginResponse.setMessage("Login successful!");
		loginResponse.setUser(loggedUser);
		
		return ResponseEntity.ok(loginResponse);
	}
	
	@GetMapping("/username")
	public String currentUsername(Principal principal) {
		return principal.getName();
	}
}
