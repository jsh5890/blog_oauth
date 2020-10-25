package com.jmao.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jmao.blog.service.BoardService;

@Controller
public class BoardController {
	/*
	 * @Autowired private PrincipalDetail principalDetail;
	 */

	@Autowired
	private BoardService boardService;

	 //@AuthenticationPrincipal PrincipalDetail principalDetail
	@GetMapping({"","/"})
	public String index(Model model,@PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("board", boardService.글리스트(pageable));
		return "index";
	}

	@GetMapping({"/board/saveForm"})
	public String saveForm() {
		return "board/saveForm";
	}
}
