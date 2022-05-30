package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NoticeInfoDto;
import com.ssafy.happyhouse.model.QnaInfoDto;
import com.ssafy.happyhouse.model.service.NoticeService;
import com.ssafy.happyhouse.model.service.QnaService;

@RestController
@RequestMapping("/cs")
public class CustomerServiceController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private QnaService qnaService;

	
	@GetMapping("/notices")
	public ResponseEntity<List<NoticeInfoDto>> getNoticeRecentList(){
		logger.debug("getNoticeRecentList - 호출");
		return new ResponseEntity<List<NoticeInfoDto>>(noticeService.getNoticeRecentList(), HttpStatus.OK);
	}
	
	@GetMapping("/qna")
	public ResponseEntity<List<QnaInfoDto>> getQnaRecentList(){
		logger.debug("getQnaRecentList - 호출");
		return new ResponseEntity<List<QnaInfoDto>>(qnaService.getQnaRecentList(), HttpStatus.OK);
	}
	
	
}
