package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.NoticeInfoDto;

public interface NoticeMapper {
	public List<NoticeInfoDto> selectNoticeList();
	public List<NoticeInfoDto> selectNoticeRecentList();
	public NoticeInfoDto selectNoticeByNoticeId(int noticeId);
	public boolean insertNotice(NoticeInfoDto noticeInfoDto);
	public boolean updateNotice(NoticeInfoDto noticeInfoDto);
	public boolean deleteNotice(int noticeId);
}
