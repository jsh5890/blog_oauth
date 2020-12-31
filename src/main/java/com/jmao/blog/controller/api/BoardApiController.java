package com.jmao.blog.controller.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jmao.blog.config.auth.PrincipalDetail;
import com.jmao.blog.dto.ResponseDto;
import com.jmao.blog.model.Board;
import com.jmao.blog.model.Reply;
import com.jmao.blog.model.UploadFile;
import com.jmao.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	ResourceLoader resourceLoader;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principalDetail) {
		logger.info("UserApiController : 세이브 호출됨");
		boardService.글쓰기(board, principalDetail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board")
	public ResponseDto<Integer> deleteById(@RequestBody Map<String, String> map) {
		logger.info("map : " + map);
		int id = Integer.parseInt(map.get("id"));
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/board")
	public ResponseDto<Integer> updateBoard(@RequestBody Map<String, String> map) {
		boardService.글수정하기(map);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PostMapping("/api/image")
	public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) {
		try {
			logger.info("file : " + file);
			UploadFile uploadFile = boardService.store(file);
			return ResponseEntity.ok().body("/api/image/" + uploadFile.getId());
		} catch(Exception e) {
			logger.error(e.getLocalizedMessage(),e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/api/image/{fileId}")
	public ResponseEntity<?> serveFile(@PathVariable Integer fileId){
		try {
			UploadFile uploadFile = boardService.load(fileId);
			Resource resource = resourceLoader.getResource("file:" + uploadFile.getFilePath());
			return ResponseEntity.ok().body(resource);
		} catch(Exception e) {
			logger.error(e.getLocalizedMessage(),e);
			return ResponseEntity.badRequest().build();
		}
		
	}

	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply,@AuthenticationPrincipal PrincipalDetail principalDetail) {
		logger.info("UserApiController : 댓글 세이브");
		
		boardService.댓글쓰기(reply, principalDetail.getUser(),boardId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}