package com.lab.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class JWTService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final String KEY = "XuanLabKey";
	
	 
}
