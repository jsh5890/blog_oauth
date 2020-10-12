package com.jmao.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jmao.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	/*
	 * @Autowired private PrincipalDetail principalDetail;
	 */
	 //@AuthenticationPrincipal PrincipalDetail principalDetail
	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principalDetail) {
		System.out.println("principalDetail : " + principalDetail.getUsername());
		return "index";
	}
}
