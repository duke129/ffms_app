/**
 * 
 */
package com.happiest.minds.ffms.sales.pojo;

/**
 * @author kiran
 *
 */
public class TicketFilter {

	private String ticketNo;
	
	private String mobileNumber;

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "TicketFilter [ticketNo=" + ticketNo + ", mobileNumber=" + mobileNumber + "]";
	}
	
	
}
