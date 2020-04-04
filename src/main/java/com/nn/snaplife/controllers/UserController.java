package com.nn.snaplife.controllers;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nn.snaplife.beans.User;
import com.nn.snaplife.beans.UserService;
import com.nn.snaplife.exceptions.PasswordViolationException;

@RestController
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		User registeredUser = null;
		
		try {
			registeredUser = userService.register(user);
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(registeredUser);
	}
}
