package com.jmao.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogMavenApplication.class, args);
	}
	
	@Bean(name = "uploadPath")
	public String uploadPath() {
		return "d:/image/";
	}
}
