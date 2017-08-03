package com.arrowgs.agsadmin.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arrowgs.agsadmin.service.PasswordEncoderService;



@Service
public class PasswordEncoderServiceImpl implements PasswordEncoderService {
	
	private PasswordEncoder passwordEncoder;
	
	public PasswordEncoderServiceImpl() {
		passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public String encode(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	@Override
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

}
