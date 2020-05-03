package com.nn.snaplife.beans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

import com.nn.snaplife.exceptions.PasswordViolationException;
import com.nn.snaplife.repositories.PermissionRepository;
import com.nn.snaplife.repositories.UserRepository;

@Service
public class UserService {
	
	private User user = null;
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	private PermissionRepository permissionRepository;
	private AuthenticationManager authenticationManager;
	
	public UserService() {
		
	}
	
	@Autowired
	public UserService(PasswordEncoder passwordEncoder,
			UserRepository userRepository, PermissionRepository permissionRepository,
			AuthenticationManager authenticationManager) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.permissionRepository = permissionRepository;
		this.authenticationManager = authenticationManager;
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
	
	public User login(HttpServletRequest req, User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(email, password);
		
		Authentication auth = this.authenticationManager.authenticate(authToken);
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(auth);
		HttpSession httpSession = req.getSession(true);
		httpSession.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
		
		return user;
	}
}
