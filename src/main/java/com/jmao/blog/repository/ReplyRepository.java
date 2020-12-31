package com.jmao.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmao.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
