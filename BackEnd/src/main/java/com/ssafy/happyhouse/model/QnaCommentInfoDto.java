package com.ssafy.happyhouse.model;

public class QnaCommentInfoDto {
	private int commentId;
	private int qnaId;
	private String content;
	private String userId = "admin";
	private String date;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getQnaId() {
		return qnaId;
	}
	public void setQnaId(int qnaId) {
		this.qnaId = qnaId;
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

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "QnaCommentInfoDto [commentId=" + commentId + ", qnaId=" + qnaId + ", content=" + content + ", userId="
				+ userId + ", date=" + date + "]";
	}

}
