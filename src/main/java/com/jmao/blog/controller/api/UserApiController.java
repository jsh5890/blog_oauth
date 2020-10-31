package com.jmao.blog.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmao.blog.dto.ResponseDto;
import com.jmao.blog.model.User;
import com.jmao.blog.service.UserService;

@RestController
public class UserApiController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;

	@PostMapping("/auth/join")
	public ResponseDto<Integer> save(@RequestBody User user) {
		logger.info("UserApiController : 세이브 호출됨");

		int result = userService.회원가입(user);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
	
	@PutMapping("/auth/userUpdate")
	public ResponseDto<Integer> userUpdate(@RequestBody User user) {
		logger.info("user1234 : " + user);
		userService.회원수정(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user,HttpSession session) {
	 * logger.info("UserApiController : 로그인 호출됨");
	 *
	 * User principal = userService.로그인(user);
	 *
	 * if(principal != null) { session.setAttribute("principal", principal); }
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */


}