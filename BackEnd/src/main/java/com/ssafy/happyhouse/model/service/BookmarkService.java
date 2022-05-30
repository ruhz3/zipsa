package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BookmarkChartDto;
import com.ssafy.happyhouse.model.BookmarkInfoDto;
import com.ssafy.happyhouse.model.HouseInfoDto;

public interface BookmarkService {
	public List<HouseInfoDto> getBookmarkList(String userId);
	public HouseInfoDto getBookmarkDetail(int aptCode);
	public boolean createBookmark(BookmarkInfoDto bookmarkInfoDto);
	public boolean removeBookmark(Map<String, String> map);
	
	public BookmarkChartDto getBookmarkChartData(int aptCode);
}
