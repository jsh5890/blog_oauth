package com.jmao.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@Entity// User 클래스가 읽어서 자동으로  mysql에 테이블이 생성됨
//@DynamicInsert insert시 null을 제외시킴
public class User {

	@Id //Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스, auto_increment 전략

	@Column(nullable = false, length = 100, unique = true)
	private String username; //아디

	@Column(nullable = false, length = 100) //암호화 해야해서 넉너키
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

	private String provider;
	private String providerId;
	
	//@ColumnDefault("'user'")
	//데이터 베이스는 롤타입 이라는게 없음
	@Enumerated(EnumType.STRING)
	private RoleType role; // 나중Enum쓴다 // admin,user...권한
	
	private String oauth;// kakao,google

	@CreationTimestamp // 시간이 자동으로 입력
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime createDate;

	@CreationTimestamp // 시간이 자동으로 입력
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime updateDate;

	public User(int id, String username, String password, String email, String provider, String providerId,
			RoleType role, String oauth, LocalDateTime createDate, LocalDateTime updateDate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.provider = provider;
		this.providerId = providerId;
		this.role = role;
		this.oauth = oauth;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

}
