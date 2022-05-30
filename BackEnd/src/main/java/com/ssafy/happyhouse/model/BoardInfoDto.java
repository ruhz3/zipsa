package com.ssafy.happyhouse.model;

public class BoardInfoDto {
	private int boardId;
	private String boardGroupId;
	private String title;
	private String content;
	private String userId;
	private String date;
	private int commentCount;
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBoardGroupId() {
		return boardGroupId;
	}
	public void setBoardGroupId(String boardGroupId) {
		this.boardGroupId = boardGroupId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	@Override
	public String toString() {
		return "BoardInfoDto [boardId=" + boardId + ", boardGroupId=" + boardGroupId + ", title=" + title + ", content="
				+ content + ", userId=" + userId + ", date=" + date + ", commentCount=" + commentCount + "]";
	}

	
	
}
