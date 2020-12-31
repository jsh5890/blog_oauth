package com.jmao.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jmao.blog.model.Board;
import com.jmao.blog.repository.BoardRepository;

@RestController
public class ReplyControllerTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id)	{
		return boardRepository.findById(id).get(); //jackson 라이브러리 발동 (오브젝트 json으로 리턴) => 모델의 getter를 호출
	}
}
