package com.jmao.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Transactional(readOnly = true)
	public Page<Board> 글리스트(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 상세 보기 실패");
		});	
	}

	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	/*
	 * @Transactional(readOnly = true) public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
