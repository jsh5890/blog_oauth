package com.jmao.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmao.blog.model.User;

// DAO
// 자동으로 빈 등록됨
//@Repository // 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {

}
