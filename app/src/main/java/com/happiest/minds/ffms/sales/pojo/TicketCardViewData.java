package com.happiest.minds.ffms.sales.pojo;

import java.util.Date;
import java.util.List;

public class TicketCardViewData {
	
	private Long ticketId;
	private String ticketNumber;
	private String customerName;
	private String customerMobileNumber;
	private AddressVo customerAddress;
	private Date ticketCreationDate;
	private String committedETR;
	private List<ActivityVo> activities;
	
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}
	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}
	public AddressVo getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(AddressVo customerAddress) {
		this.customerAddress = customerAddress;
	}
	public Date getTicketCreationDate() {
		return ticketCreationDate;
	}
	public void setTicketCreationDate(Date ticketCreationDate) {
		this.ticketCreationDate = ticketCreationDate;
	}
	public String getCommittedETR() {
		return committedETR;
	}
	public void setCommittedETR(String committedETR) {
		this.committedETR = committedETR;
	}
	
	
	
	public List<ActivityVo> getActivities() {
		return activities;
	}
	public void setActivities(List<ActivityVo> activities) {
		this.activities = activities;
	}
	@Override
	public String toString() {
		return "TicketCardViewData [ticketId=" + ticketId + ", ticketNumber=" + ticketNumber + ", customerName="
				+ customerName + ", customerMobileNumber=" + customerMobileNumber + ", customerAddress="
				+ customerAddress + ", ticketCreationDate=" + ticketCreationDate + ", committedETR=" + committedETR
				+ "]";
	}
	
	
	
	
	

}
