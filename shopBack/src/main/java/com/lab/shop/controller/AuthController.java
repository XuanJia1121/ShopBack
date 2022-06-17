package com.lab.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.shop.service.RedisService;

@RestController
public class AuthController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/rSet")
	public void set() {
		redisService.setByKey("test","hello world"); 
	}
	
	@RequestMapping("/rGet")
	public String get() {
		return redisService.selectByKey("test");
	}
	
}