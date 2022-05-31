package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.UserInfoDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("로그인 API")
@Controller
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService userService;

	@ApiOperation("아이디, 비밀번호가 맞으면 JWT를 제공합니다.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserInfoDto userInfoDto){
		logger.debug("login - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserInfoDto loginUser = userService.login(userInfoDto);
			if(loginUser != null) {
				String token = jwtService.create("userId",  loginUser.getUserId(), "access-token"); // key, data, subject
				logger.debug("로그인 토큰 정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch(Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}

	@ApiOperation("JWT 토큰이 유효하다면, 회원 정보를 반환합니다.")
	@GetMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> getUserInfo(@PathVariable("userId") String userId, HttpServletRequest request){
		logger.debug("userId : ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if(jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!!!");
			try {
				// 로그인 사용자 정보 
				UserInfoDto userInfoDto = userService.getUserInfo(userId);
				resultMap.put("userInfoDto", userInfoDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch(Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else {
			logger.error("사용 볼가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@ApiOperation("회원가입합니다.")
	@PostMapping
	public ResponseEntity<String> signup(@RequestBody UserInfoDto userInfoDto){
		logger.debug("userInfoDto info : {}", userInfoDto);
		try {
			if(userService.signup(userInfoDto))	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			else return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.CONFLICT);
		}

		
	}
	@ApiOperation("회원 정보를 수정합니다.")
	@PutMapping
	public ResponseEntity<String> modify(@RequestBody UserInfoDto userInfoDto) throws Exception{
		logger.debug("modifyUser : ", userInfoDto);
		if(userService.modifyUserInfo(userInfoDto))	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation("회원 탈퇴합니다.")
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody UserInfoDto userInfoDto) throws Exception{
		logger.debug("deleteUser : ", userInfoDto);
		if(userService.removeUserInfo(userInfoDto.getUserId()))	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation("분실한 비밀번호를 찾습니다.")
	@PostMapping("/findpw")
	public ResponseEntity<String> findPassword(@RequestBody UserInfoDto userInfoDto) throws Exception{
		logger.debug("findPassword : ", userInfoDto.getUserPassword());
		if(userService.findPassword(userInfoDto) == null) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}else {
			String uuid = UUID.randomUUID().toString().replace("-", "");
			uuid = uuid.substring(0, 10);
			userInfoDto.setUserPassword(uuid);
			if(userService.modifyUserInfo(userInfoDto)) {
				userService.sendMail(uuid, userInfoDto.getUserEmail());
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			else	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}


}
