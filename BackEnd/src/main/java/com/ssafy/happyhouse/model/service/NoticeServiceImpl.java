package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NoticeInfoDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<NoticeInfoDto> getNoticeList(){
		return sqlSession.getMapper(NoticeMapper.class).selectNoticeList();
	}
	
	@Override
	public List<NoticeInfoDto> getNoticeRecentList(){
		return sqlSession.getMapper(NoticeMapper.class).selectNoticeRecentList();
	}

	@Override
	public NoticeInfoDto getNoticeDetail(int noticeId) {
		return sqlSession.getMapper(NoticeMapper.class).selectNoticeByNoticeId(noticeId);
	}

	@Override
	public boolean createNotice(NoticeInfoDto noticeInfoDto) {
		return sqlSession.getMapper(NoticeMapper.class).insertNotice(noticeInfoDto);
	}

	@Override
	public boolean modifyNotice(NoticeInfoDto noticeInfoDto) {
		return sqlSession.getMapper(NoticeMapper.class).updateNotice(noticeInfoDto);
	}

	@Override
	public boolean removeNotice(int noticeId) {
		return sqlSession.getMapper(NoticeMapper.class).deleteNotice(noticeId);
	}

}
