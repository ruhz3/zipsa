package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.QnaCommentInfoDto;
import com.ssafy.happyhouse.model.QnaInfoDto;
import com.ssafy.happyhouse.model.service.QnaService;

@RestController
@RequestMapping("/qna")
@CrossOrigin("*")
public class QnaController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnaService qnaService;
	
	@GetMapping
	public ResponseEntity<List<QnaInfoDto>> getQnaList(QnaInfoDto qnaInfoDto){
		logger.info("getQnaList - 호출 : ");
		return new ResponseEntity<List<QnaInfoDto>>(qnaService.getQnaList(), HttpStatus.OK);
	}
	
	@GetMapping("/recent")
	public ResponseEntity<List<QnaInfoDto>> getQnaRecentList(QnaInfoDto qnaInfoDto){
		logger.info("getQnaRecentList - 호출 : ");
		return new ResponseEntity<List<QnaInfoDto>>(qnaService.getQnaRecentList(), HttpStatus.OK);
	}
	
	@GetMapping("{qnaId}")
	public ResponseEntity<QnaInfoDto> getQnaDetail(@PathVariable int qnaId){
		logger.info("getQnaDetail - 호출");
		return new ResponseEntity<QnaInfoDto>(qnaService.getQnaDetail(qnaId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createQna(@RequestBody QnaInfoDto qnaInfoDto){
		logger.info("createQna - 호출 ");
		if(qnaService.createQna(qnaInfoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{qndId}")
	public ResponseEntity<String> modifyQna(@RequestBody QnaInfoDto qnaInfoDto){
		logger.info("modifyQna - 호출");
		if(qnaService.modifyQna(qnaInfoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{qnaId}")
	public ResponseEntity<String> removeQna(@PathVariable int qnaId){
		logger.info("removeQna - 호출");
		qnaService.removeCommentList(qnaId);
		if(qnaService.removeQna(qnaId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/comments/{qnaId}")
	public ResponseEntity<List<QnaCommentInfoDto>> getCommentList(@PathVariable int qnaId){
		logger.info("getCommentList - 호출");
		return new ResponseEntity<List<QnaCommentInfoDto>>(qnaService.getCommentList(qnaId), HttpStatus.OK);
	}
	
	@PostMapping("/comments")
	public ResponseEntity<String> createComment(@RequestBody QnaCommentInfoDto qnaCommentInfoDto){
		logger.info("createComment - 호출");
		if(qnaService.createComment(qnaCommentInfoDto)) {
			qnaService.modifyCommentCount();
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/comments")
	public ResponseEntity<String> modifyComment(@RequestBody QnaCommentInfoDto qnaCommentInfoDto){
		logger.info("modifyComment - 호출");
		if(qnaService.modifyComment(qnaCommentInfoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<String> removeComment(@PathVariable int commentId){
		logger.info("removecomment - 호출");
		if(qnaService.removeComment(commentId)) {
			qnaService.modifyCommentCount();
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{qnaId}/comments")
	public ResponseEntity<String> removeCommentList(@PathVariable int qnaId){
		logger.info("removeCommentList - 호출");
		if(qnaService.removeCommentList(qnaId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
