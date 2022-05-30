package com.ssafy.happyhouse.model;

public class FileInfoDto {
	
	private int fileId;
	private int boardId;
	private String originName;
	private String saveName;
	private long fileSize;
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	@Override
	public String toString() {
		return "FileInfoDto [fileId=" + fileId + ", boardId=" + boardId + ", originName=" + originName + ", saveName="
				+ saveName + ", fileSize=" + fileSize + "]";
	}
	
	
	
	
	
}
