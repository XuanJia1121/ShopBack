package com.lab.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.shop.dto.AuthRequest;
import com.lab.shop.dto.ResponseDto;
import com.lab.shop.enums.ResponseEnum;
import com.lab.shop.service.JWTService;
import com.lab.shop.utils.ResponseUtil;

@RequestMapping("/auth")
@Controller
public class AuthController {

	@Autowired
	private JWTService jWTService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping("/googleSuccess.action")
	public void googleLoginSuccess(OAuth2AuthenticationToken authentication,HttpServletResponse response) throws Exception {
		Map<String,Object> authMap = authentication.getPrincipal().getAttributes();
		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername((String)authMap.get("name"));
		authRequest.setToken(jWTService.generateToken(authRequest));
		ResponseDto dto = new ResponseDto();
		dto.setCode(ResponseEnum.LOGIN_SUC.getCode());
		dto.setMsg(ResponseEnum.LOGIN_SUC.getMsg());
		dto.setData(objectMapper.writeValueAsString(authRequest));
		ResponseUtil.writeResponse(response, objectMapper.writeValueAsString(dto));
		response.sendRedirect("http://localhost:8080/");
	}

}
