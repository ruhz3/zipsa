package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NoticeInfoDto;
import com.ssafy.happyhouse.model.QnaCommentInfoDto;
import com.ssafy.happyhouse.model.QnaInfoDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;
import com.ssafy.happyhouse.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<QnaInfoDto> getQnaList() {
		return sqlSession.getMapper(QnaMapper.class).selectQnaList();
	}
	
	@Override
	public List<QnaInfoDto> getQnaRecentList(){
		return sqlSession.getMapper(QnaMapper.class).selectQnaRecentList();
	}

	@Override
	public QnaInfoDto getQnaDetail(int qnaId) {
		return sqlSession.getMapper(QnaMapper.class).selectQnaByQnaId(qnaId);
	}

	@Override
	public boolean createQna(QnaInfoDto qnaInfoDto) {
		return sqlSession.getMapper(QnaMapper.class).insertQna(qnaInfoDto);
	}

	@Override
	public boolean modifyQna(QnaInfoDto qnaInfoDto) {
		return sqlSession.getMapper(QnaMapper.class).updateQna(qnaInfoDto);
	}

	@Override
	public boolean removeQna(int qnaId) {
		return sqlSession.getMapper(QnaMapper.class).deleteQna(qnaId);
	}

	@Override
	public List<QnaCommentInfoDto> getCommentList(int qnaId) {
		return sqlSession.getMapper(QnaMapper.class).selectCommentList(qnaId);
	}

	@Override
	public int getCommentCount(int qnaId) {
		return sqlSession.getMapper(QnaMapper.class).getCommentCountByQnaId(qnaId);
	}
	
	@Override
	public boolean modifyCommentCount() {
		return sqlSession.getMapper(QnaMapper.class).updateCommentCount();
	}

	@Override
	public boolean createComment(QnaCommentInfoDto qnaCommentInfoDto) {
		return sqlSession.getMapper(QnaMapper.class).insertComment(qnaCommentInfoDto);
	}
	@Override
	public boolean modifyComment(QnaCommentInfoDto qnaCommentInfoDto) {
		return sqlSession.getMapper(QnaMapper.class).updateComment(qnaCommentInfoDto);
	}

	@Override
	public boolean removeComment(int commentId) {
		return sqlSession.getMapper(QnaMapper.class).deleteComment(commentId);
	}

	@Override
	public boolean removeCommentList(int qnaId) {
		return sqlSession.getMapper(QnaMapper.class).deleteCommentList(qnaId);
	}

}
