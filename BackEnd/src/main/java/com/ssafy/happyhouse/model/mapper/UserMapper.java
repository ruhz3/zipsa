package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserInfoDto;

@Mapper
public interface UserMapper {
	
	UserInfoDto login(UserInfoDto userInfoDto) throws SQLException;	
	UserInfoDto getUserInfo(String userId);
	boolean modifyUserInfo(UserInfoDto userInfoDto);
	boolean signup(UserInfoDto userInfoDto);
	boolean removeUserInfo(String userId) throws SQLException;
	UserInfoDto findPassword(UserInfoDto userInfoDto);
}
