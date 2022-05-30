package com.ssafy.happyhouse.model.service;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhouse.model.BoardCommentInfoDto;
import com.ssafy.happyhouse.model.BoardInfoDto;
import com.ssafy.happyhouse.model.FileInfoDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.happyhouse.model.mapper.FileMapper;
import com.ssafy.happyhouse.util.FileUtils;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	FileUtils fileUtils;

	@Override
	public List<BoardInfoDto> getBoardList(String boardGroupId) {
		return sqlSession.getMapper(BoardMapper.class).selectBoardList(boardGroupId);
	}

	@Override
	public List<BoardInfoDto> getBoardRecentList(String boardGroupId) {
		return sqlSession.getMapper(BoardMapper.class).selectBoardRecentList(boardGroupId);
	}

	@Override
	public BoardInfoDto getBoardDetail(int boardId) {
		return sqlSession.getMapper(BoardMapper.class).selectBoardByBoardId(boardId);
	}

	@Override
	public boolean createBoard(BoardInfoDto boardInfoDto) {
		return sqlSession.getMapper(BoardMapper.class).insertBoard(boardInfoDto); 
	}
	
	@Override
	public boolean createBoard(BoardInfoDto boardInfoDto, MultipartFile[] files) {
		if(sqlSession.getMapper(BoardMapper.class).insertBoard(boardInfoDto)) {
			List<FileInfoDto> fileList = fileUtils.uploadFiles(files, boardInfoDto.getBoardId());
			if(!CollectionUtils.isEmpty(fileList)) {
				return sqlSession.getMapper(FileMapper.class).insertFile(fileList);
			}else {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean modifyBoard(BoardInfoDto boardInfoDto) {
		return sqlSession.getMapper(BoardMapper.class).updateBoard(boardInfoDto);
	}
	
	@Override
	public boolean modifyBoard(BoardInfoDto boardInfoDto, MultipartFile[] files) {
		sqlSession.getMapper(FileMapper.class).deleteFile(boardInfoDto.getBoardId());
		if(sqlSession.getMapper(BoardMapper.class).updateBoard(boardInfoDto)) {
			List<FileInfoDto> fileList = fileUtils.uploadFiles(files, boardInfoDto.getBoardId());
			if(!CollectionUtils.isEmpty(fileList)) {
				return sqlSession.getMapper(FileMapper.class).insertFile(fileList);
			}else {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeBoard(int boardId) {
		sqlSession.getMapper(FileMapper.class).deleteFile(boardId);
		return sqlSession.getMapper(BoardMapper.class).deleteBoard(boardId);
	}

	@Override
	public List<FileInfoDto> getFileList(int boardId) {
		int fileTotalCount = sqlSession.getMapper(FileMapper.class).selectFileTotalCount(boardId);
		if(fileTotalCount < 1) {
			return Collections.emptyList();
		}
		return sqlSession.getMapper(FileMapper.class).selectFileListByBoardId(boardId);
	}

	@Override
	public List<BoardCommentInfoDto> getCommentList(int boardId) {
		return sqlSession.getMapper(BoardMapper.class).selectCommentList(boardId);
	}

	@Override
	public int getCommentCount(int boardId) {
		return sqlSession.getMapper(BoardMapper.class).getCommentCountByBoardId(boardId);
	}

	@Override
	public boolean modifyCommentCount() {
		return sqlSession.getMapper(BoardMapper.class).updateCommentCount();
	}

	@Override
	public boolean createComment(BoardCommentInfoDto boardCommentInfoDto) {
		return sqlSession.getMapper(BoardMapper.class).insertComment(boardCommentInfoDto);
	}

	@Override
	public boolean modifyComment(BoardCommentInfoDto boardCommentInfoDto) {
		return sqlSession.getMapper(BoardMapper.class).updateComment(boardCommentInfoDto);
	}

	@Override
	public boolean removeComment(int commentId) {
		return sqlSession.getMapper(BoardMapper.class).deleteComment(commentId);
	}
	
	

}
