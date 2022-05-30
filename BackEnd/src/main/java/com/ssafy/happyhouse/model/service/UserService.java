package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.UserInfoDto;

public interface UserService {

	UserInfoDto login(UserInfoDto userInfoDto) throws SQLException;
	UserInfoDto getUserInfo(String userId);
	boolean modifyUserInfo(UserInfoDto userInfoDto);
	boolean signup(UserInfoDto userInfoDto);
	boolean removeUserInfo(String userId) throws SQLException;
	UserInfoDto findPassword(UserInfoDto userInfoDto);
	void sendMail(String userPassword, String userEmail);
	
}
