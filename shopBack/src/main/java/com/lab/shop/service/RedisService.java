package com.lab.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
	
	@Autowired
	private JedisPool jedisPool;
	
	public String selectByKey(String key) {
		Jedis jedis = jedisPool.getResource();
//		if (jedis.exists(key)) {
//			return jedis.get(key);
//		}
//		return null;
		jedis.keys("*").stream().forEach(t -> {
			System.out.println(t);
		});
		return "~";
	}
	
	public void setByKey(String key,String val) {
		Jedis jedis = jedisPool.getResource();
		System.out.println(jedis.isConnected());
		System.out.println(jedis.set(key, val));
	}
	
}
