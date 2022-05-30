package com.ssafy.happyhouse.model;

public class BoardCommentInfoDto {
	private int commentId;
	private int boardId;
	private String content;
	private String userId;
	private String date;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "BoardCommentInfoDto [commentId=" + commentId + ", boardId=" + boardId + ", content=" + content
				+ ", userId=" + userId + ", date=" + date + "]";
	}
	
	

}
