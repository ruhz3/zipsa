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

import com.ssafy.happyhouse.model.NoticeInfoDto;
import com.ssafy.happyhouse.model.service.NoticeService;

@RestController
@RequestMapping("/notices")
@CrossOrigin("*")
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
	
	
	@GetMapping
	public ResponseEntity<List<NoticeInfoDto>> getNoticeList(){
		logger.debug("getNoticeList - 호출");
		return new ResponseEntity<List<NoticeInfoDto>>(noticeService.getNoticeList(), HttpStatus.OK);
	}
	
	@GetMapping("/recent")
	public ResponseEntity<List<NoticeInfoDto>> getNoticeRecentList(){
		logger.debug("getNoticeList - 호출");
		return new ResponseEntity<List<NoticeInfoDto>>(noticeService.getNoticeRecentList(), HttpStatus.OK);
	}

	
	@GetMapping("{noticeId}")
	public ResponseEntity<NoticeInfoDto> getNoticeDetail(@PathVariable int noticeId){
		logger.debug("getNoticeDetail - 호출");
		return new ResponseEntity<NoticeInfoDto>(noticeService.getNoticeDetail(noticeId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createNotice(@RequestBody NoticeInfoDto noticeInfoDto){
		logger.debug("createNotice - 호출");
		if(noticeService.createNotice(noticeInfoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{noticeId}")
	public ResponseEntity<String> modifyNotice(@RequestBody NoticeInfoDto noticeInfoDto){
		logger.debug("modifyNotice - 호출");
		if(noticeService.modifyNotice(noticeInfoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{noticeId}")
	public ResponseEntity<String> removeNotice(@PathVariable int noticeId){
		logger.debug("removeNotice - 호출");
		if(noticeService.removeNotice(noticeId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);			
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
}
