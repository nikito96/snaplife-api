package com.nn.snaplife.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	
	private PasswordEncoder passwordEncoder;
	private ApplicationDetailsService applicationDetailsService;
	
	@Autowired
	public ApplicationSecurity(PasswordEncoder passwordEncoder,
			ApplicationDetailsService applicationDetailsService) {
		this.passwordEncoder = passwordEncoder;
		this.applicationDetailsService = applicationDetailsService;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/loginPage", "/appLogin", "/users", "/register", "/registerPage", "/scripts/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll()
		.defaultSuccessUrl("/users")
		.and().logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(applicationDetailsService).passwordEncoder(passwordEncoder);
	}
	
}
