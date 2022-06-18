package com.lab.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.shop.service.RedisService;

@RestController
public class AuthController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/rSet")
	public void set() {
		redisService.setByKey("test1","hello 2");
	}
	
	@Cacheable("user")
	@RequestMapping("/rGet")
	public String get() {
		return redisService.getByKey("test1");
	} 
	
	@CacheEvict("user")
	@RequestMapping("/rDel")
	public void del() {
		
	}
}