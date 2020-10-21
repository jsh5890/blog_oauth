package com.jmao.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jmao.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티가 로긴을 가로채고 로긴될때 유저디테일즈에서 오브젝트에 시큐리티 세션이 저장됨
@Getter
public class PrincipalDetail implements UserDetails{
	private User user;//컴포지션

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴 아래부터 트루 값들이 되는것들
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있는지 않았는지 리턴
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비번만료여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 활성화여부
	@Override
	public boolean isEnabled() {
		return true;
	}

	//계정권한 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collection = new ArrayList<>();
		/*
		 * collection.add(new GrantedAuthority() {
		 *
		 * @Override public String getAuthority() { return "ROLE_"+user.getRole(); //
		 * ROLE_USER 이런식 리턴 } });
		 */
		//collection.add(()->"ROLE_"+user.getRole());

		  collection.add(()->{ return "ROLE_"+user.getRole(); });

		return collection;
	}
}
