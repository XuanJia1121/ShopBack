package com.lab.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/shop")
@RestController
public class ShopController {
	
	@GetMapping("/noAuth/test.action")
	public String test() {
		return "Do Not Login !";
	}
	
	@GetMapping("/auth/test.action")
	public String test2() {
		return "Login success!";
	}
	 
}
