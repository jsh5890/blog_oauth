package com.jmao.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmao.blog.model.Board;
import com.jmao.blog.model.User;
import com.jmao.blog.repository.BoardRepository;

//스프링 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. loC를 해준다.
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void 글쓰기(Board board, User user) {
		try {
			board.setCount(0);
			board.setUser(user);
			boardRepository.save(board);
		} catch (Exception e) {
			System.out.println("글쓰기 : " + e.getLocalizedMessage());
		}
	}

	public List<Board> 글리스트() {
		return boardRepository.findAll();
	}

	/*
	 * @Transactional(readOnly = true) public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
