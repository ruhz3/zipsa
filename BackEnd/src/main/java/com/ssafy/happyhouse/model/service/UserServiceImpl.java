package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.UserInfoDto;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	private static final String FROM_ADDRESS = "minryu95@gmail.com";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserInfoDto login(UserInfoDto userInfoDto) throws SQLException { //로그인
		if(userInfoDto.getUserId() == null || userInfoDto.getUserPassword() == null)	return null;
		return sqlSession.getMapper(UserMapper.class).login(userInfoDto);
	}
	
	@Override
	public UserInfoDto getUserInfo(String userId) {
		return sqlSession.getMapper(UserMapper.class).getUserInfo(userId);
	}

	@Override
	public boolean signup(UserInfoDto userInfo){ //회원가입
		return sqlSession.getMapper(UserMapper.class).signup(userInfo); 
	}
	
	@Override
	public boolean removeUserInfo(String userId) throws SQLException { //회원탈퇴
		return sqlSession.getMapper(UserMapper.class).removeUserInfo(userId);
	}
	
	@Override
	public boolean modifyUserInfo(UserInfoDto userInfo) {
		return sqlSession.getMapper(UserMapper.class).modifyUserInfo(userInfo);
	}
	@Override
	public UserInfoDto findPassword(UserInfoDto userInfoDto) {
		return sqlSession.getMapper(UserMapper.class).findPassword(userInfoDto);
	}

	@Override
	public void sendMail(String userPassword, String userEmail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(userEmail);
		message.setFrom(FROM_ADDRESS);
		message.setSubject("[임시] 비밀번호 찾기");
		message.setText("비밀번호는 " + userPassword + "입니다.");
		mailSender.send(message);
	}
	
}
