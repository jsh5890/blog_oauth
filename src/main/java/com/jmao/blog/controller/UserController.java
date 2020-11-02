package com.jmao.blog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

//인증이 안된 사용자들이 출입할수 있는 경로 /auth/**
// 그냥 주소가 /면 index.jsp
// static 이하의 있는 js,css,image

@Controller
public class UserController {

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/auth/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code) {
		// post 방식
		RestTemplate rt = new RestTemplate();
		
		//HttpHeaders오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//httpbody 오브젝트 생성
		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.add("grant_type", "authorization_code");
		param.add("client_id", "0e0a1a3805f19f56cd093b4484789bdf");
		param.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		param.add("code", code);
		
		//합본HttpHeaders,httpbody 하나에 담고
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(param,headers);
		
		// http요청 포스트방식으로 reponse 응답받음 
		ResponseEntity<String> responseEntity 
		= rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,kakaoTokenRequest,String.class);
		
		return "카카오토근 요청 완료 : " + responseEntity;
	}
}
