package com.jmao.blog.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jmao.blog.model.User;
import com.jmao.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로긴요청을 가로챌때 유저네임이라는 변수랑 패스워드 변수 가로채는데
	//패스워드는 알아서 비교하고 유저네임만 가져옴
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		if (user != null) {
			return new PrincipalDetail(user);
		}
		return null;
	}
}
