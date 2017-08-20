package com.arrowgs.agsadmin.service;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface PasswordEncoderService {
	
	PasswordEncoder getPasswordEncoder();
	
	String encode(String password);
	
	boolean matches(String rawPassword, String encodedPassword);
}
