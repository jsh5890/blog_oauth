package com.jmao.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmao.blog.model.UploadFile;

public interface UploadFileRepository extends JpaRepository<UploadFile, Integer>{
}