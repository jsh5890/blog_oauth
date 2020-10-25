package com.jmao.blog.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jmao.blog.config.auth.PrincipalDetail;
import com.jmao.blog.dto.ResponseDto;
import com.jmao.blog.model.Board;
import com.jmao.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principalDetail) {
		System.out.println("UserApiController : 세이브 호출됨");
		boardService.글쓰기(board, principalDetail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/boardDelete")
	public ResponseDto<Integer> deleteById(@RequestBody Map<String, String> map) {
		System.out.println("map : " + map);
		int id = Integer.parseInt(map.get("id"));
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}