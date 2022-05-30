package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BookmarkChartDto;
import com.ssafy.happyhouse.model.BookmarkInfoDto;
import com.ssafy.happyhouse.model.HouseInfoDto;

public interface BookmarkMapper {
	public List<HouseInfoDto> selectBookmarkList(String userId);
	public HouseInfoDto selectBookmarkByAptCode(int aptCode);
	public boolean insertBookmark(BookmarkInfoDto bookmarkInfoDto);
	public boolean deleteBookmark(Map<String, String> map);
	
	public BookmarkChartDto selectBookmarkChartData(int aptCode);
}
