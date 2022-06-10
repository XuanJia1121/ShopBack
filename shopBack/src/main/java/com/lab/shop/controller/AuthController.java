package com.lab.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {
	
	@RequestMapping("/noAuth/success")
	public String googleLogin() {
		return "google success";
	}
	
}
