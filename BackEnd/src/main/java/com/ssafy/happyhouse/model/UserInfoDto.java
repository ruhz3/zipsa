package com.ssafy.happyhouse.model;

public class UserInfoDto {

	private String userId;
	private String userPassword;
	private String userName;
	private String userAddress;
	private String userPhone;
	private String userEmail;
	private int isAdmin = 0;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	
	@Override
	public String toString() {
		return "UserInfoDto [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName
				+ ", userAddress=" + userAddress + ", userPhone=" + userPhone + ", userEmail=" + userEmail
				+ ", isAdmin=" + isAdmin + "]";
	}
	


}
