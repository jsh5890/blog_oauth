package com.jmao.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jmao.blog.config.auth.PrincipalDetailService;

// 빈등록 : 스프링 컨테이너에서 객체를 관리할수 있게하는것

@Configuration // 빈등록 ioc관리
@EnableWebSecurity // 시큐리티 필터 추가 = 스프링시큐리티가 활성이 되어있는데 어떤설정을 파일에서 하게따.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정주소 권한체크하게따
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf 비활성화
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/css/**", "/js/**", "/image/**", "/dummy/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/login")	 // 스프링 시큐리티가 로긴을 가로채서 대신 로긴
				.defaultSuccessUrl("/");
				//.failureUrl("/fail");
	}

	@Bean
	public BCryptPasswordEncoder encodingPassword() {
		return new BCryptPasswordEncoder();
	}

	//시큐리티 암호화 해쉬 비교 방법
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodingPassword());
	}
}
