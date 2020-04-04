package com.nn.snaplife.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nn.snaplife.exceptions.PasswordViolationException;
import com.nn.snaplife.repositories.PermissionRepository;
import com.nn.snaplife.repositories.UserRepository;

@Service
public class UserService {
	
	private User user = null;
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	private PermissionRepository permissionRepository;
	
	public UserService() {
		
	}
	
	@Autowired
	public UserService(PasswordEncoder passwordEncoder,
			UserRepository userRepository, PermissionRepository permissionRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.permissionRepository = permissionRepository;
	}
	
	public User register(User user) throws PasswordViolationException {
		this.user = user;
		
		String password = this.user.getPassword();
		
		if (password.length() < 8 || password.length() > 30) {
			throw new PasswordViolationException("Password should be between 8 and 40 symbols!");
		}
		
		String encodedPassword = passwordEncoder.encode(password);
		Permission permission = this.permissionRepository.findByPermission("USER");
		this.user.setPassword(encodedPassword);
		this.user.setPermission(permission);
		User registeredUser = userRepository.save(user);
		return registeredUser;
	}
}
