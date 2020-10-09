package com.jmao.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmao.blog.model.User;
import com.jmao.blog.repository.UserRepository;

//스프링 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. loC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public int 회원가입(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			System.out.println("회원가입 : " + e.getLocalizedMessage());
		}

		return -1;
	}

	@Transactional(readOnly = true)
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
