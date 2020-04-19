package com.nn.snaplife.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nn.snaplife.beans.User;
import com.nn.snaplife.repositories.UserRepository;

@Component
public class AuthenticationProviderService implements AuthenticationProvider {
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AuthenticationProviderService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		User user = this.userRepository.findByEmail(email);
		
		String encodedPassword = user.getPassword();
		
		boolean passwordMatch = this.passwordEncoder.matches(password, encodedPassword);
		
		if (user != null && passwordMatch) {
			return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
