package com.nn.snaplife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
public class SnaplifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnaplifeApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEcnEncoder() {
		return new BCryptPasswordEncoder();
	}
}
