package com.ssafy.happyhouse.model;

public class BookmarkInfoDto {
	private int bookmarkNo;
	private String userId;
	private int aptCode;
	
	public int getBookmarkNo() {
		return bookmarkNo;
	}
	public void setBookmarkNo(int bookmarkNo) {
		this.bookmarkNo = bookmarkNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	@Override
	public String toString() {
		return "BookmarkInfoDto [bookmarkNo=" + bookmarkNo + ", userId=" + userId + ", aptCode=" + aptCode + "]";
	}

	
}
