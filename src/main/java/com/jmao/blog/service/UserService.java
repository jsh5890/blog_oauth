package com.jmao.blog.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmao.blog.config.auth.PrincipalDetail;
import com.jmao.blog.model.RoleType;
import com.jmao.blog.model.User;
import com.jmao.blog.repository.UserRepository;

//스프링 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. loC를 해준다.
@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encpass;

	@Transactional
	public int 회원가입(User user) {
		try {
			user.setRole(RoleType.USER);
			user.setPassword(encpass.encode(user.getPassword()));
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			logger.info("회원가입 : " + e.getLocalizedMessage());
		}

		return -1;
	}

	@Transactional
	public void 회원수정(User user) {
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		persistance.setPassword(encpass.encode(user.getPassword()));
		persistance.setEmail(user.getEmail());
		LocalDateTime currentDate = LocalDateTime.now();
		persistance.setUpdateDate(currentDate);
		//회원수정 서비스 종료시 트랜젝션이 종료되고 더티체킹이 되어 업데이트 commit 됨(영속화됨)
	}

	/*
	 * @Transactional(readOnly = true) public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
