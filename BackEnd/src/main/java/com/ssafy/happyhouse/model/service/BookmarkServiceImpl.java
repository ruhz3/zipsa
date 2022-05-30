package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.BookmarkChartDto;
import com.ssafy.happyhouse.model.BookmarkInfoDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.mapper.BookmarkMapper;

@Service
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<HouseInfoDto> getBookmarkList(String userId) {
		return sqlSession.getMapper(BookmarkMapper.class).selectBookmarkList(userId);
	}
	
	@Override
	public HouseInfoDto getBookmarkDetail(int aptCode) {
		return sqlSession.getMapper(BookmarkMapper.class).selectBookmarkByAptCode(aptCode);
	}

	@Override
	public boolean createBookmark(BookmarkInfoDto bookmarkInfoDto) {
		return sqlSession.getMapper(BookmarkMapper.class).insertBookmark(bookmarkInfoDto);
	}

	@Override
	public boolean removeBookmark(Map<String, String> map) {
		return sqlSession.getMapper(BookmarkMapper.class).deleteBookmark(map);
	}

	@Override
	public BookmarkChartDto getBookmarkChartData(int aptCode) {
		return sqlSession.getMapper(BookmarkMapper.class).selectBookmarkChartData(aptCode);
	}

}
