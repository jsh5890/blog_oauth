package com.jmao.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogControllerTest {
		
		@GetMapping("/test/hello")
		public String hello() {
			return "<h1>하이루 스프링</h1>";
		}
}
