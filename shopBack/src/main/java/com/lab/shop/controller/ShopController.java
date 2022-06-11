package com.lab.shop.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/shop")
@RestController
public class ShopController {
	
	@PostMapping("/test.action")
	public String test() {
		return "hello shop";
	}
	
}
