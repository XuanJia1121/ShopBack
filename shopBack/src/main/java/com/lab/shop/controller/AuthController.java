package com.lab.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.shop.dto.AuthRequest;
import com.lab.shop.dto.ResponseDto;
import com.lab.shop.enums.ResponseEnum;
import com.lab.shop.service.JWTService;

@RequestMapping("/auth")
@RestController
public class AuthController {

	@Autowired
	private JWTService jWTService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping("/googleSuccess.action")
	public String googleLoginSuccess(OAuth2AuthenticationToken authentication,HttpServletResponse response) throws Exception {
		Map<String,Object> authMap = authentication.getPrincipal().getAttributes();
		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername((String)authMap.get("name"));
		authRequest.setToken(jWTService.generateToken(authRequest));
		ResponseDto dto = new ResponseDto();
		dto.setCode(ResponseEnum.LOGIN_SUC.getCode());
		dto.setMsg(ResponseEnum.LOGIN_SUC.getMsg());
		dto.setData(objectMapper.writeValueAsString(authRequest));
		return objectMapper.writeValueAsString(dto);
	}

}
