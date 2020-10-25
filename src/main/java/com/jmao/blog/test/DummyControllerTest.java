package com.jmao.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmao.blog.model.RoleType;
import com.jmao.blog.model.User;
import com.jmao.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	//localhost:8000/blog/dummy/join
	// http의 바디에 유저네임,패스워드,이멜 데이터가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) {
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
	
	//localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user가 4를 찾으면 내가 디비에서 못찾으면 유저가 null이 될거아냐
		// 그럼 플그램에 문제가 잇겟지? 그래서 optional로 너의 User객체를 가져올테니 null체크하고 return해
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당유저는 없습니다. id : " + id);
			}
		});
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size = 1,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> page = userRepository.findAll(pageable); 
		
		List<User> user = page.getContent();
		return page;
	}
	
	//save함수는 아이디를 전달하지않으면 insert id전달하면 업데이트하는데 널값들도 널로업뎃함
	@Transactional // 함수종료시 자동커밋이됨
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		logger.info("id : " + id);
		logger.info("getPassword : " + requestUser.getPassword());
		logger.info("getEmail : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//requestUser.setId(id);
		//requestUser.setUsername("aaaa");
		
		//userRepository.save(requestUser);
		
		//더티체킹
		return null;
	}
	
	@DeleteMapping("/dummy/userDelete/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제실패했습니다. 해당id는 없습니다.";
		}
		
		return "삭제되었습니다.id : " + id;
	}
	
}
