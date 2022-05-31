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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Q&A API")
@RestController
@RequestMapping("/qna")
@CrossOrigin("*")
public class QnaController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnaService qnaService;
	
	@ApiOperation("Q&A 질문 목록을 반환합니다.")
	@GetMapping
	public ResponseEntity<List<QnaInfoDto>> getQnaList(QnaInfoDto qnaInfoDto){
		logger.info("getQnaList - 호출 : ");
		return new ResponseEntity<List<QnaInfoDto>>(qnaService.getQnaList(), HttpStatus.OK);
	}
	
	@ApiOperation("Q&A의 최근 5개 게시글 질문 목록을 반환합니다.")
	@GetMapping("/recent")
	public ResponseEntity<List<QnaInfoDto>> getQnaRecentList(QnaInfoDto qnaInfoDto){
		logger.info("getQnaRecentList - 호출 : ");
		return new ResponseEntity<List<QnaInfoDto>>(qnaService.getQnaRecentList(), HttpStatus.OK);
	}
	
	@ApiOperation("하나의 Q&A 질문을 반환합니다.")
	@GetMapping("{qnaId}")
	public ResponseEntity<QnaInfoDto> getQnaDetail(@PathVariable int qnaId){
		logger.info("getQnaDetail - 호출");
		return new ResponseEntity<QnaInfoDto>(qnaService.getQnaDetail(qnaId), HttpStatus.OK);
	}
	
	@ApiOperation("Q&A 질문을 생성합니다.")
	@PostMapping
	public ResponseEntity<String> createQna(@RequestBody QnaInfoDto qnaInfoDto){
		logger.info("createQna - 호출 ");
		if(qnaService.createQna(qnaInfoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation("Q&A을 질문을 수정합니다.")
	@PutMapping("{qndId}")
	public ResponseEntity<String> modifyQna(@RequestBody QnaInfoDto qnaInfoDto){
		logger.info("modifyQna - 호출");
		if(qnaService.modifyQna(qnaInfoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation("Q&A을 질문을 삭제합니다.")
	@DeleteMapping("{qnaId}")
	public ResponseEntity<String> removeQna(@PathVariable int qnaId){
		logger.info("removeQna - 호출");
		qnaService.removeCommentList(qnaId);
		if(qnaService.removeQna(qnaId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation("질문에 해당하는 답변 목록을 반환합니다.")
	@GetMapping("/comments/{qnaId}")
	public ResponseEntity<List<QnaCommentInfoDto>> getCommentList(@PathVariable int qnaId){
		logger.info("getCommentList - 호출");
		return new ResponseEntity<List<QnaCommentInfoDto>>(qnaService.getCommentList(qnaId), HttpStatus.OK);
	}
	
	@ApiOperation("답변을 생성합니다.")
	@PostMapping("/comments")
	public ResponseEntity<String> createComment(@RequestBody QnaCommentInfoDto qnaCommentInfoDto){
		logger.info("createComment - 호출");
		if(qnaService.createComment(qnaCommentInfoDto)) {
			qnaService.modifyCommentCount();
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation("답변을 수정합니다.")
	@PutMapping("/comments")
	public ResponseEntity<String> modifyComment(@RequestBody QnaCommentInfoDto qnaCommentInfoDto){
		logger.info("modifyComment - 호출");
		if(qnaService.modifyComment(qnaCommentInfoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation("댓글을 삭제합니다.")
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<String> removeComment(@PathVariable int commentId){
		logger.info("removecomment - 호출");
		if(qnaService.removeComment(commentId)) {
			qnaService.modifyCommentCount();
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation("질문에 해당하는 답변을 모두 삭제합니다.")
	@DeleteMapping("/{qnaId}/comments")
	public ResponseEntity<String> removeCommentList(@PathVariable int qnaId){
		logger.info("removeCommentList - 호출");
		if(qnaService.removeCommentList(qnaId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
