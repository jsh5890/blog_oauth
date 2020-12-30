package com.jmao.blog.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jmao.blog.model.Board;
import com.jmao.blog.model.UploadFile;
import com.jmao.blog.model.User;
import com.jmao.blog.repository.BoardRepository;
import com.jmao.blog.repository.UploadFileRepository;

//스프링 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. loC를 해준다.
@Service
public class BoardService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	UploadFileRepository uploadFileRepository;
	
	private final Path rootLocation; // d:/image/
	
	public BoardService(String uploadPath) {
		this.rootLocation = Paths.get(uploadPath);
		System.out.println(rootLocation.toString());
	}

	@Transactional
	public void 글쓰기(Board board, User user) {
		try {
			board.setCount(0);
			board.setUser(user);
			boardRepository.save(board);
		} catch (Exception e) {
			logger.info("글쓰기 : " + e.getLocalizedMessage());
		}
	}

	@Transactional(readOnly = true)
	public Page<Board> 글리스트(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 상세 보기 실패");
		});	
	}

	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(Map<String, String> map) {
		Board board = boardRepository.findById(Integer.parseInt(map.get("id"))).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패");
		}); // 영속화 완료	
		board.setTitle(map.get("title"));
		board.setContent(map.get("content"));
		// 해당함수 종료시 트랜잭션 종료, 이때 더티체킹일어나면서 자동업뎃됨 디비에 flush 커밋
	}

	public UploadFile store(MultipartFile file) throws Exception { 
		//		 fileName : 예류2.jpg
		//		 filePath : d:/images/uuid-예류2.jpg
		//		 saveFileName : uuid-예류2.png
		//		 contentType : image/jpeg
		//		 size : 4994942
		//		 registerDate : 2020-02-06 22:29:57.748
		try {
			if(file.isEmpty()) {
				logger.info("file2 : " + file);
				throw new Exception("Failed to store empty file " + file.getOriginalFilename());
			}
			logger.info("file3 : " + file);
			
			String saveFileName = fileSave(rootLocation.toString(), file); 
			UploadFile saveFile = new UploadFile();
			saveFile.setFileName(file.getOriginalFilename());
			saveFile.setSaveFileName(saveFileName);
			saveFile.setContentType(file.getContentType());
			saveFile.setSize(file.getResource().contentLength());
			saveFile.setRegisterDate(LocalDateTime.now());
			saveFile.setFilePath(rootLocation.toString().replace(File.separatorChar, '/') +'/' + saveFileName);   
			uploadFileRepository.save(saveFile);
			return saveFile;
			
		} catch(IOException e) {
			throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
		}
		
		
	}

	public UploadFile load(Integer fileId) {
		return uploadFileRepository.findById(fileId).get();
	}
	
	public String fileSave(String rootLocation, MultipartFile file) throws IOException {
		File uploadDir = new File(rootLocation);
		
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		// saveFileName 생성
		UUID uuid = UUID.randomUUID();
		String saveFileName = uuid.toString() + file.getOriginalFilename();
		File saveFile = new File(rootLocation, saveFileName);
		FileCopyUtils.copy(file.getBytes(), saveFile);
		
		return saveFileName;
	}


	/*
	 * @Transactional(readOnly = true) public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
