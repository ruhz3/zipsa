package com.ssafy.happyhouse.model;

public class QnaInfoDto {
	private String qnaId;
	private String title;
	private String content;
	private String userId;
	private int commentCount;
	private String date;
	
	public String getQnaId() {
		return qnaId;
	}
	public void setQnaId(String qnaId) {
		this.qnaId = qnaId;
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
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "QnaInfoDto [qnaId=" + qnaId + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", commentCount=" + commentCount + ", date=" + date + "]";
	}
	
	
	
}
