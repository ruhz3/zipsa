package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NoticeInfoDto;

public interface NoticeService {
	public List<NoticeInfoDto> getNoticeList();
	public List<NoticeInfoDto> getNoticeRecentList();
	public NoticeInfoDto getNoticeDetail(int noticeId);
	public boolean createNotice(NoticeInfoDto noticeInfoDto);
	public boolean modifyNotice(NoticeInfoDto noticeInfoDto);
	public boolean removeNotice(int noticeId);
}
