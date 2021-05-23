package com.jmao.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmao.blog.model.User;

// DAO
// 자동으로 빈 등록됨
//@Repository // 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {
	// JPA 네이밍 전략
	// select * from user where username = ? and password = ?2;
	//User findByUsernameAndPassword(String username, String password);
	//select * from user where username = 1?;
	//Optional<User> findByUsername(String username);
	public User findByUsername(String username);
}
