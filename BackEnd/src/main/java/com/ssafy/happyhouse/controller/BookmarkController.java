package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.BookmarkChartDto;
import com.ssafy.happyhouse.model.BookmarkInfoDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.service.BookmarkService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("관심단지 API")
@RestController
@RequestMapping("/bookmark")
@CrossOrigin("*")
public class BookmarkController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	BookmarkService bookmarkService;
	
	@ApiOperation("사용자의 관심단지 목록을 반환합니다.")
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<HouseInfoDto>> getBookmarkList(@PathVariable String userId){
		logger.debug("getBookmarkList - 호출");
		return new ResponseEntity<List<HouseInfoDto>>(bookmarkService.getBookmarkList(userId), HttpStatus.OK);
	}
	
	@ApiOperation("하나의 관심단지의 상세정보를 반환합니다.")
	@GetMapping("{aptCode}")
	public ResponseEntity<HouseInfoDto> getBookmarkDetail(@PathVariable int aptCode){
		logger.debug("getBookmarkDetail - 호출");
		return new ResponseEntity<HouseInfoDto>(bookmarkService.getBookmarkDetail(aptCode), HttpStatus.OK);
	}
	
	@ApiOperation("사용자의 관심단지 목록에 등록합니다.")	
	@PostMapping
	public ResponseEntity<String> createBookmark(@RequestBody BookmarkInfoDto bookmarkInfoDto){
		logger.debug("createBookmark - 호출");
		if(bookmarkService.createBookmark(bookmarkInfoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation("사용자의 관심단지 목록에서 삭제합니다.")	
	@DeleteMapping("/{userId}/{aptCode}")
	public ResponseEntity<String> removeBookmark(@PathVariable String userId, @PathVariable int aptCode){
		logger.debug("deleteBookmark - 호출");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("aptCode", Integer.toString(aptCode));
		
		if(bookmarkService.removeBookmark(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	@ApiOperation("하나의 관심단지에 대해 비교차트를 위한 데이터를 반환합니다.")	
	@GetMapping("/chart/{aptCode}")
	public ResponseEntity<BookmarkChartDto> getChartDataList(@PathVariable int aptCode){
		logger.debug("getChartDataList - 호출");
		return new ResponseEntity<BookmarkChartDto>(bookmarkService.getBookmarkChartData(aptCode), HttpStatus.OK);
	}
			
}
