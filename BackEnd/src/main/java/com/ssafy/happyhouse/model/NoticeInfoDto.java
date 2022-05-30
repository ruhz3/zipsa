package com.ssafy.happyhouse.model;

public class NoticeInfoDto {
	private String noticeId;
	private String title;
	private String content;
	private String userId;
	private String date;
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
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
	@Override
	public String toString() {
		return "NoticeInfoDto [noticeId=" + noticeId + ", title=" + title + ", content=" + content + ", userId="
				+ userId + ", date=" + date + "]";
	}

	
}
