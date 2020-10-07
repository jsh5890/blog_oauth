package com.jmao.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmao.blog.dto.ResponseDto;
import com.jmao.blog.model.User;

@RestController
public class UserApiController {
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : 세이브 호출됨");
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
}	