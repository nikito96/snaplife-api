package com.nn.snaplife.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nn.snaplife.beans.User;
import com.nn.snaplife.repositories.UserRepository;

@Service
public class ApplicationDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Autowired
	public ApplicationDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final User user = userRepository.findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException("User with email " + email + "does not exists!");
		}
		return new UserPrincipal(user);
	}

}
