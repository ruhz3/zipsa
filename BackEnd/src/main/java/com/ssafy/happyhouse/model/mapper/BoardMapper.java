package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.BoardCommentInfoDto;
import com.ssafy.happyhouse.model.BoardInfoDto;

public interface BoardMapper {
	public List<BoardInfoDto> selectBoardList(String boardGroupId);
	public List<BoardInfoDto> selectBoardRecentList(String boardGroupId);
	public BoardInfoDto selectBoardByBoardId(int boardId);
	public boolean insertBoard(BoardInfoDto boardInfoDto);
	public boolean updateBoard(BoardInfoDto boardInfoDto);
	public boolean deleteBoard(int boardId);
	
	public List<BoardCommentInfoDto> selectCommentList(int boardId);
	public int getCommentCountByBoardId(int boardId);
	public boolean updateCommentCount();
	public boolean insertComment(BoardCommentInfoDto boardCommentInfoDto);
	public boolean updateComment(BoardCommentInfoDto boardCommentInfoDto);
	public boolean deleteComment(int commentId);
	
}
