package com.ssafy.happyhouse.model;

public class BookmarkChartDto {
	private int aptCode;
	private String aptName;
	private String maxArea;
	private String recentPrice;
	private int buildYear;
	private int dealCount;
	private int maxfloor;
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getMaxArea() {
		return maxArea;
	}
	public void setMaxArea(String maxArea) {
		this.maxArea = maxArea;
	}
	public String getRecentPrice() {
		return recentPrice;
	}
	public void setRecentPrice(String recentPrice) {
		this.recentPrice = recentPrice;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public int getDealCount() {
		return dealCount;
	}
	public void setDealCount(int dealCount) {
		this.dealCount = dealCount;
	}
	public int getMaxfloor() {
		return maxfloor;
	}
	public void setMaxfloor(int maxfloor) {
		this.maxfloor = maxfloor;
	}
	@Override
	public String toString() {
		return "BookmarkChartDto [aptCode=" + aptCode + ", aptName=" + aptName + ", maxArea=" + maxArea
				+ ", recentPrice=" + recentPrice + ", buildYear=" + buildYear + ", dealCount=" + dealCount
				+ ", maxfloor=" + maxfloor + "]";
	}

	
}
