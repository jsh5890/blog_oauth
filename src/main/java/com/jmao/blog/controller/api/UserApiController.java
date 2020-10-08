package com.jmao.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmao.blog.dto.ResponseDto;
import com.jmao.blog.model.RoleType;
import com.jmao.blog.model.User;
import com.jmao.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : 세이브 호출됨");

		user.setRole(RoleType.USER);
		int result = userService.회원가입(user);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
}