package com.jmao.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답
@RestController
public class HttpControllerTest {
		
	//http://localhost:8080/http/get {select}
	@GetMapping("/http/get")
	public String  getTest(Member m) {
		return "get요청 : " + m.getId()+",s ";
	}
	//http://localhost:8080/http/post	 {insert}
	@PostMapping("/http/post")
	public String  postTest(@RequestBody Member m) {
		return "post요청 : " + m.getId()+ " " + m.getPassword();
	}
	//http://localhost:8080/http/put {update}
	@PutMapping("/http/put")
	public String  putTest() {
		return "put요청";
	}
	//http://localhost:8080/http/http {delete}
	@DeleteMapping("/http/delete")
	public String  deleteTest() {
		return "delete요청";
	}
}
