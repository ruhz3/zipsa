package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhouse.model.BoardCommentInfoDto;
import com.ssafy.happyhouse.model.BoardInfoDto;
import com.ssafy.happyhouse.model.FileInfoDto;

public interface BoardService {
	public List<BoardInfoDto> getBoardList(String boardGroupId);
	public List<BoardInfoDto> getBoardRecentList(String boardGroupId);
	public BoardInfoDto getBoardDetail(int boardId);
	public boolean createBoard(BoardInfoDto boardInfoDto);
	public boolean createBoard(BoardInfoDto boardInfoDto, MultipartFile[] files);
	public boolean modifyBoard(BoardInfoDto boardInfoDto);
	public boolean modifyBoard(BoardInfoDto boardInfoDto, MultipartFile[] files);
	public boolean removeBoard(int boardId);
	
	public List<FileInfoDto> getFileList(int boardId);
	
	public List<BoardCommentInfoDto> getCommentList(int boardId);
	public int getCommentCount(int boardId);
	public boolean modifyCommentCount();
	public boolean createComment(BoardCommentInfoDto boardCommentInfoDto);
	public boolean modifyComment(BoardCommentInfoDto boardCommentInfoDto);
	public boolean removeComment(int commentId);
	

}
