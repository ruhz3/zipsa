package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhouse.model.BoardCommentInfoDto;
import com.ssafy.happyhouse.model.BoardInfoDto;
import com.ssafy.happyhouse.model.QnaCommentInfoDto;
import com.ssafy.happyhouse.model.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list/{boardGroupId}")
	public ResponseEntity<List<BoardInfoDto>> getBoardList(@PathVariable String boardGroupId){
		logger.debug("getBoardList - 호출");
		return new ResponseEntity<List<BoardInfoDto>>(boardService.getBoardList(boardGroupId), HttpStatus.OK);
	}
	
	@GetMapping("/list/recent/{boardGroupId}")
	public ResponseEntity<List<BoardInfoDto>> getBoardRecentList(@PathVariable String boardGroupId){
		logger.debug("getBoardRecentList - 호출");
		return new ResponseEntity<List<BoardInfoDto>>(boardService.getBoardRecentList(boardGroupId), HttpStatus.OK);
	}
	
	@GetMapping("{boardId}")
	public ResponseEntity<BoardInfoDto> getBoardDetail(@PathVariable int boardId){
		logger.debug("getBoardDetail - 호출");
		boardService.modifyCommentCount();
		return new ResponseEntity<BoardInfoDto>(boardService.getBoardDetail(boardId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createBoard(@RequestPart(value="boardInfoDto")BoardInfoDto boardInfoDto, @RequestPart(value="files")final MultipartFile[] files){
		
		logger.debug("createBoard - 호출");
		if(boardService.createBoard(boardInfoDto, files)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PostMapping("{boardId}")
	public ResponseEntity<String> modifyBoard(@RequestPart(value="boardInfoDto")BoardInfoDto boardInfoDto, @RequestPart(value="files")final MultipartFile[] files){
		logger.debug("modifyBoard - 호출");
		if(boardService.modifyBoard(boardInfoDto, files)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{boardId}")
	public ResponseEntity<String> removeBoard(@PathVariable int boardId){
		logger.debug("removeBoard - 호출");
		if(boardService.removeBoard(boardId)) {
			
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);			
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/comments/{boardId}")
	public ResponseEntity<List<BoardCommentInfoDto>> getCommentList(@PathVariable int boardId){
		logger.info("getCommentList - 호출");
		return new ResponseEntity<List<BoardCommentInfoDto>>(boardService.getCommentList(boardId), HttpStatus.OK);
	}
	
	@PostMapping("/comments")
	public ResponseEntity<String> createComment(@RequestBody BoardCommentInfoDto boardCommentInfoDto){
		logger.info("createComment - 호출");
		if(boardService.createComment(boardCommentInfoDto)) {
			boardService.modifyCommentCount();
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/comments")
	public ResponseEntity<String> modifyComment(@RequestBody BoardCommentInfoDto boardCommentInfoDto){
		logger.info("modifyComment - 호출");
		if(boardService.modifyComment(boardCommentInfoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<String> removeComment(@PathVariable int commentId){
		logger.info("removecomment - 호출");
		if(boardService.removeComment(commentId)) {
			boardService.modifyCommentCount();
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
}
