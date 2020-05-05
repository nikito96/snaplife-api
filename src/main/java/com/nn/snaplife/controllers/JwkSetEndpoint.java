package com.nn.snaplife.controllers;

import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;

@FrameworkEndpoint
public class JwkSetEndpoint {
	
	private KeyPair KeyPair;
	
	@Autowired
	public JwkSetEndpoint(KeyPair keyPair) {
		this.KeyPair = keyPair;
	}
	
	@GetMapping("/.well-known/jwks.json")
	@ResponseBody
	public Map<String, Object> getKey(Principal principal) {
		RSAPublicKey publicKey = (RSAPublicKey) this.KeyPair.getPublic();
		RSAKey key = new RSAKey.Builder(publicKey).build();
		return new JWKSet(key).toJSONObject();
	}
}
