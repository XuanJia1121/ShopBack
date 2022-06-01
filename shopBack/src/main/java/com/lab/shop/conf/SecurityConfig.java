package com.lab.shop.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.lab.shop.service.AuthFailService;
import com.lab.shop.service.AuthSuccessService;

@Configuration
public class SecurityConfig {

	@Autowired
	private AuthSuccessService authSuccessService;
	@Autowired
	private AuthFailService authFailService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.antMatcher("/**")
				.authorizeRequests(authorize -> authorize.anyRequest()
				.authenticated()).build();
	}
}
