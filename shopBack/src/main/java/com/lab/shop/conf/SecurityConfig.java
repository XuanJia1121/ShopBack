package com.lab.shop.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.lab.shop.encode.UserPasswordEncoder;
import com.lab.shop.service.AuthFailService;
import com.lab.shop.service.AuthSuccessService;
import com.lab.shop.service.LogoutService;
import com.lab.shop.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthSuccessService authSuccessService;
	@Autowired
	private AuthFailService authFailService;
	@Autowired
	private LogoutService logoutService;
	@Autowired
	private UserService userService;
	
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
         .loginProcessingUrl("/auth/login.action")
         .permitAll()
         .usernameParameter("username")
         .passwordParameter("password")
         .successHandler(authSuccessService)
         .failureHandler(authFailService)
         .and()
         .logout()
         .logoutUrl("/auth/logout.action")
         .permitAll()
         .logoutSuccessHandler(logoutService)
         .logoutSuccessUrl("/api/shop/home.action");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userService)
		.passwordEncoder(new UserPasswordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
