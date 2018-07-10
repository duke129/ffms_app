package com.happiest.minds.ffms.sales.pojo;

public class DashBoardSummaryCountVo {

	private int statusId;
	private String statusName;
	private int totalCounts;
	
	public String getStatusName() {
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	public int getTotalCounts() {
		return totalCounts;
	}
	
	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}
	
	

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "DashBoardSummaryCountVo [statusName=" + statusName + ", totalCounts=" + totalCounts + "]";
	}
	
	

}
