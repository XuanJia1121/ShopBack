package com.lab.shop.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
	
	@Value("${spring.redis.host}")
    String host;
    @Value("${spring.redis.port}")
    int port;
    @Value("${spring.redis.timeout}")
    int time;
    @Value("${spring.redis.jedis.pool.max-active}")
    int poolTotal;
    @Value("${spring.redis.jedis.pool.max-idle}")
    int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    int minIdle;
	
    @Bean
    public RedisTemplate<String, String> template(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<String, String>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }
    
    @Bean
    public JedisPool JedisPoolUtil() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setTestOnBorrow(true);
        poolConfig.setMaxTotal(poolTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setTestWhileIdle(true);
        return new JedisPool(poolConfig, host, port);
    }
    
}
