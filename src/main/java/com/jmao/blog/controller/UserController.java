package com.jmao.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
