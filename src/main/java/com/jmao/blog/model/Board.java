package com.jmao.blog.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id // Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment 전략

	@Column(nullable = false, length = 100)
	private String title; // 제목

	@Lob // 섬머노트 라이브러리 html 태그 섞어서 드러감
	private String content; // 내용

	@ColumnDefault("0")
	private int count; // 조회수

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId") // userId로 컬럼생성됨
	private User user; // DB는 오브젝트 저장X Fk, 자바는 오브젝트를 저장가능

	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관관계주인 x 리플테이블 board가 포링키
	private List<Reply> reply;

	@CreationTimestamp // 시간이 자동으로 입력
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime createDate;
	
	@CreationTimestamp // 시간이 자동으로 입력
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime updateDate;

}
