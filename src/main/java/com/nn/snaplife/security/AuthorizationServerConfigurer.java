package com.nn.snaplife.security;

import java.security.KeyPair;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.nn.snaplife.repositories.UserRepository;

@Import(AuthorizationServerEndpointsConfiguration.class)
@EnableResourceServer
@Configuration
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
	
	private AuthenticationManager AuthenticationManager;
	private KeyPair KeyPair;
	@Autowired PasswordEncoder passwordEncoder;
	
	@Autowired
	public AuthorizationServerConfigurer(AuthenticationConfiguration authenticationConfiguration,
			KeyPair keyPair) throws Exception {
		this.AuthenticationManager = authenticationConfiguration.getAuthenticationManager();
		this.KeyPair = keyPair;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.AuthenticationManager)
			.accessTokenConverter(accessTokenConverter())
			.tokenStore(tokenStore());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
        .inMemory()
            .withClient("android")
            .secret(this.passwordEncoder.encode("androidsecret"))
            .scopes("resource:any")
            .authorizedGrantTypes("password");
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setKeyPair(this.KeyPair);
		return converter;
	}
}
