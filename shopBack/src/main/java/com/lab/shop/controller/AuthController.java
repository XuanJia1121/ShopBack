package com.lab.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@GetMapping("/noAuth/google")
	public String googleLogin() {
		return "google success";
	}
	
}
