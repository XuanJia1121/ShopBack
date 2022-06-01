package com.lab.shop.utils;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.AuthException;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtils implements Serializable {

	private static final long serialVersionUID = -3691707496887886382L;
	private static final long EXPIRATION_TIME = 1 * 60 * 1000;
	private static final String SECRET = "secretKey";

	public static String generateToken(HashMap<String, String> userDetails) {
		Map<String, Object> claims = new HashMap<>();
		userDetails.forEach((k, v) -> {
			claims.put(k, v);
		});
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
	}

	public static boolean validateToken(String token) throws AuthException {
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		if (StringUtils.isNotBlank(claims.get("username").toString())) {
			return true;
		}
		throw new AuthException("Invalid JWT TOKEN");
	}
}
