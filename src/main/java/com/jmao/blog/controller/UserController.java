package com.jmao.blog.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmao.blog.config.auth.PrincipalDetail;
import com.jmao.blog.model.KakaoProfile;
import com.jmao.blog.model.OAuthToken;
import com.jmao.blog.model.User;
import com.jmao.blog.service.UserService;

//인증이 안된 사용자들이 출입할수 있는 경로 /auth/**
// 그냥 주소가 /면 index.jsp
// static 이하의 있는 js,css,image

@Controller
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/auth/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	/*
	 * @GetMapping("/auth/kakao/callback") public String kakaoCallback(String code)
	 * { // post 방식 RestTemplate rt = new RestTemplate();
	 * 
	 * //HttpHeaders오브젝트 생성 HttpHeaders headers = new HttpHeaders();
	 * headers.add("Content-type",
	 * "application/x-www-form-urlencoded;charset=utf-8");
	 * 
	 * //httpbody 오브젝트 생성 MultiValueMap<String, String> param = new
	 * LinkedMultiValueMap<>(); param.add("grant_type", "authorization_code");
	 * param.add("client_id", "0e0a1a3805f19f56cd093b4484789bdf");
	 * param.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
	 * param.add("code", code);
	 * 
	 * //합본HttpHeaders,httpbody 하나에 담고 HttpEntity<MultiValueMap<String, String>>
	 * kakaoTokenRequest = new HttpEntity<>(param,headers);
	 * 
	 * // http요청 포스트방식으로 reponse 응답받음 ResponseEntity<String> responseEntity =
	 * rt.exchange("https://kauth.kakao.com/oauth/token",
	 * HttpMethod.POST,kakaoTokenRequest,String.class);
	 * 
	 * ObjectMapper obMapper = new ObjectMapper();
	 * 
	 * OAuthToken oAuthToken = null; try { oAuthToken =
	 * obMapper.readValue(responseEntity.getBody(), OAuthToken.class); } catch
	 * (JsonMappingException e) { logger.info(e.getLocalizedMessage(),e); } catch
	 * (JsonProcessingException e) { logger.info(e.getLocalizedMessage(),e); }
	 * 
	 * logger.info("getAccess_token() : " + oAuthToken.getAccess_token());
	 * RestTemplate rt2 = new RestTemplate();
	 * 
	 * //HttpHeaders오브젝트 생성 HttpHeaders headers2 = new HttpHeaders();
	 * headers2.add("Authorization","Bearer "+oAuthToken.getAccess_token());
	 * headers2.add("Content-type",
	 * "application/x-www-form-urlencoded;charset=utf-8");
	 * 
	 * //합본HttpHeaders,httpbody 하나에 담고 HttpEntity<MultiValueMap<String, String>>
	 * kakaoprofileRequest = new HttpEntity<>(headers2);
	 * 
	 * // http요청 포스트방식으로 reponse 응답받음 ResponseEntity<String> response2 =
	 * rt2.exchange("https://kapi.kakao.com/v2/user/me" , HttpMethod.POST ,
	 * kakaoprofileRequest, String.class); logger.info("response2 : " +
	 * response2.getBody());
	 * 
	 * ObjectMapper obMapper2 = new ObjectMapper();
	 * 
	 * KakaoProfile kakaoProfile = null; try { kakaoProfile =
	 * obMapper2.readValue(response2.getBody(), KakaoProfile.class); } catch
	 * (JsonMappingException e) { logger.info(e.getLocalizedMessage(),e); } catch
	 * (JsonProcessingException e) { logger.info(e.getLocalizedMessage(),e); }
	 * 
	 * //user오브젝트 id,password,email logger.info("카카오 아이디 : " +
	 * kakaoProfile.getId()); logger.info("카카오 이메일 : " +
	 * kakaoProfile.getKakao_account().getEmail());
	 * 
	 * logger.info("블로그 아이디 : " + "Kakao_" + kakaoProfile.getId());
	 * logger.info("블로그 이메일 : " + kakaoProfile.getKakao_account().getEmail());
	 * //UUID tempPassword = UUID.randomUUID();
	 * 
	 * User kakaoUser = User.builder() .username("Kakao_" + kakaoProfile.getId())
	 * .password(pakaoKey) .oauth("kakao")
	 * .email(kakaoProfile.getKakao_account().getEmail()).build();
	 * 
	 * //가입자 비가입자 체크 User registedUser = userService.회원찾기(kakaoUser.getUsername());
	 * logger.info("registedUser : " + registedUser); if(registedUser.getUsername()
	 * == null) { userService.회원가입(kakaoUser); }
	 * 
	 * Authentication authentication = authenticationManager.authenticate(new
	 * UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), pakaoKey));
	 * SecurityContextHolder.getContext().setAuthentication(authentication);
	 * 
	 * return "redirect:/"; }
	 */
}
