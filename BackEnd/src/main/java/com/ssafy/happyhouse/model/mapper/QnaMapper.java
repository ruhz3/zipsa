package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.QnaCommentInfoDto;
import com.ssafy.happyhouse.model.QnaInfoDto;

public interface QnaMapper {
	public List<QnaInfoDto> selectQnaList();
	public List<QnaInfoDto> selectQnaRecentList();
	public QnaInfoDto selectQnaByQnaId(int qnaId);
	public boolean insertQna(QnaInfoDto qnaInfoDto);
	public boolean updateQna(QnaInfoDto qnaInfoDto);
	public boolean deleteQna(int qnaId);
	
	public List<QnaCommentInfoDto> selectCommentList(int qnaId);
	public int getCommentCountByQnaId(int qnaId);
	public boolean updateCommentCount();
	public boolean insertComment(QnaCommentInfoDto qnaCommentInfoDto);
	public boolean updateComment(QnaCommentInfoDto qnaCommentInfoDto);
	public boolean deleteComment(int commentId);
	public boolean deleteCommentList(int qnaId);
}
