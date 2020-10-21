package com.jmao.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmao.blog.model.Board;

// DAO
// 자동으로 빈 등록됨
//@Repository // 생략가능
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
