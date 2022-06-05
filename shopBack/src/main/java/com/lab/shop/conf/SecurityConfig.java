package com.lab.shop.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.lab.shop.service.AuthFailService;
import com.lab.shop.service.AuthSuccessService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthSuccessService authSuccessService;
	@Autowired
	private AuthFailService authFailService;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
         .authorizeRequests()
         .antMatchers(HttpMethod.POST, "/api/shop/**").permitAll()
         .anyRequest().authenticated()
         .and()
         .cors()
         .and()
         .csrf().disable()
         .formLogin()
         .loginProcessingUrl("/login")
         .usernameParameter("username")
         .passwordParameter("password")
         .successHandler(authSuccessService)
         .failureHandler(authFailService);
		 
	}
	
}
