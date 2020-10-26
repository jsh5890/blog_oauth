package com.jmao.blog.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void 글쓰기(Board board, User user) {
		try {
			board.setCount(0);
			board.setUser(user);
			boardRepository.save(board);
		} catch (Exception e) {
			logger.info("글쓰기 : " + e.getLocalizedMessage());
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

	@Transactional
	public void 글수정하기(Map<String, String> map) {
		Board board = boardRepository.findById(Integer.parseInt(map.get("id"))).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패");
		}); // 영속화 완료	
		board.setTitle(map.get("title"));
		board.setContent(map.get("content"));
		// 해당함수 종료시 트랜잭션 종료, 이때 더티체킹일어나면서 자동업뎃됨 디비에 flush 커밋
	}

	/*
	 * @Transactional(readOnly = true) public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
