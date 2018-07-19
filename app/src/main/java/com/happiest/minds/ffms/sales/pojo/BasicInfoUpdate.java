package com.happiest.minds.ffms.sales.pojo;

public class BasicInfoUpdate {
	
	private Long ticketId;
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNumber;
	private String alternateMobileNumber;
	private String officeNumber;
	private String emailId;
	private String alternateEmailId;
	private AddressVo communicationAddress;
	private AddressVo currentAddress;
	private String preferredCallTime;
	
	private Long branchId;
	private Long areaId;
	private String rejectionReason;
	
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String tittle) {
		this.title = tittle;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAlternateMobileNumber() {
		return alternateMobileNumber;
	}
	public void setAlternateMobileNumber(String alternateMobileNumber) {
		this.alternateMobileNumber = alternateMobileNumber;
	}
	public String getOfficeNumber() {
		return officeNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAlternateEmailId() {
		return alternateEmailId;
	}
	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}
	public AddressVo getCommunicationAddress() {
		return communicationAddress;
	}
	public void setCommunicationAddress(AddressVo communicationAddress) {
		this.communicationAddress = communicationAddress;
	}
	public AddressVo getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(AddressVo currentAddress) {
		this.currentAddress = currentAddress;
	}
	
	
	public String getPreferredCallTime() {
		return preferredCallTime;
	}
	public void setPreferredCallTime(String preferredCallTime) {
		this.preferredCallTime = preferredCallTime;
	}
	
	
	
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	
	
	
	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	@Override
	public String toString() {
		return "BasicInfoUpdate [ticketId=" + ticketId + ", title=" + title + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", mobileNumber=" + mobileNumber
				+ ", alternateMobileNumber=" + alternateMobileNumber + ", officeNumber=" + officeNumber + ", emailId="
				+ emailId + ", alternateEmailId=" + alternateEmailId + ", communicationAddress=" + communicationAddress
				+ ", currentAddress=" + currentAddress + ", preferredCallTime=" + preferredCallTime + ", branchId="
				+ branchId + ", areaId=" + areaId + "]";
	}
	
	
	
	

}
