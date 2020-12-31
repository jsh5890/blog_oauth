package com.jmao.blog.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jmao.blog.service.BoardService;

import antlr.collections.List;

@Controller
public class BoardController {
	/*
	 * @Autowired private PrincipalDetail principalDetail;
	 */
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;

	 //@AuthenticationPrincipal PrincipalDetail principalDetail
	@GetMapping({"","/"})
	public String index(Model model,@PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("board", boardService.글리스트(pageable));
		return "index";
	}

	@GetMapping({"/board/regist"})
	public String saveForm() {
		return "board/regist";
	}
	
	@GetMapping({"/board/view/{id}"})
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("boardView", boardService.글상세보기(id));
		//logger.info("boardView : " + boardService.글상세보기(id));
		return "board/view";
	}
	
	@GetMapping({"/board/{id}/update"})
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("boardUpdate", boardService.글상세보기(id));
		logger.info("업뎃 상세보기 :" + boardService.글상세보기(id));
		return "board/update";
	}
}
