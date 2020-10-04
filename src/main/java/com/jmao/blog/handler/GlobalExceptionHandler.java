package com.jmao.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController

public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	private String handleArgumentException(IllegalArgumentException e) {
		return  "<h1>"+e.getMessage()+"</h1>" ;
	}
}
