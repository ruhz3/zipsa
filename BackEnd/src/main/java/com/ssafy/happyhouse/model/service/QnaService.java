package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NoticeInfoDto;
import com.ssafy.happyhouse.model.QnaCommentInfoDto;
import com.ssafy.happyhouse.model.QnaInfoDto;

public interface QnaService {
	public List<QnaInfoDto> getQnaList();
	public List<QnaInfoDto> getQnaRecentList();
	public QnaInfoDto getQnaDetail(int qnaId);
	public boolean createQna(QnaInfoDto qnaInfoDto);
	public boolean modifyQna(QnaInfoDto qnaInfoDto);
	public boolean removeQna(int qnaId);
	
	public List<QnaCommentInfoDto> getCommentList(int qnaId);
	public int getCommentCount(int qnaId);
	public boolean modifyCommentCount();
	public boolean createComment(QnaCommentInfoDto qnaCommentInfoDto);
	public boolean modifyComment(QnaCommentInfoDto qnaCommentInfoDto);
	public boolean removeComment(int commentId);
	public boolean removeCommentList(int qnaId);
}
