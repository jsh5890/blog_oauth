package com.jmao.blog.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/temp/home")
	public String tempHome() {
		logger.info("tempHome()");
		// 파일 기본경로 : src/main/resources/static
		
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		logger.info("tempJsp()");
		// 파일 기본경로 : src/main/resources/static
		
		return "test";
	}
}
