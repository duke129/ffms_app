/**
 * 
 */
package com.happiest.minds.ffms.sales.pojo;

import java.util.Date;
/**
 * @author kiran
 *
 */
public class TicketDetails {
	
	private Long ticketId;
	private String ticketNo;
	private String ticketDescription;
	private Long currentAssigneeId;
	private String currentAssigneeName;
	private Integer ticketStatus;
	private Date ticketCreatedTime;
	private Long ticketTypeId;
	private String ticketTypeName;
	private String ticketSource;
	private Date prefferdCallTime;
	
	private Long assetId;
	private Long assetTypeId;
	private String assetTypeName;
	private String assetDescription;
	private String assetInstallationAddress;
	private String assetLat;
	private String assetLong;
	
	private Long customerId;
	private String customerTittle;
	private String customerFirstName;
	private String customerMiddletName;
	private String customerLastName;
	private String customerMobileNumber;
	private String customerAternateMobileNumber;
	private String customerOfficeNumber;
	private String customerEmailId;
	private String customerAternateEmailId;
	private String customerCommunicationAddress;
	
	
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getTicketDescription() {
		return ticketDescription;
	}
	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}
	public Long getCurrentAssigneeId() {
		return currentAssigneeId;
	}
	public void setCurrentAssigneeId(Long currentAssigneeId) {
		this.currentAssigneeId = currentAssigneeId;
	}
	public String getCurrentAssigneeName() {
		return currentAssigneeName;
	}
	public void setCurrentAssigneeName(String currentAssigneeName) {
		this.currentAssigneeName = currentAssigneeName;
	}
	public Integer getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(Integer ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public Date getTicketCreatedTime() {
		return ticketCreatedTime;
	}
	public void setTicketCreatedTime(Date ticketCreatedTime) {
		this.ticketCreatedTime = ticketCreatedTime;
	}
	public Long getTicketTypeId() {
		return ticketTypeId;
	}
	public void setTicketTypeId(Long ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}
	public String getTicketTypeName() {
		return ticketTypeName;
	}
	public void setTicketTypeName(String ticketTypeName) {
		this.ticketTypeName = ticketTypeName;
	}
	public String getTicketSource() {
		return ticketSource;
	}
	public void setTicketSource(String ticketSource) {
		this.ticketSource = ticketSource;
	}
	public Date getPrefferdCallTime() {
		return prefferdCallTime;
	}
	public void setPrefferdCallTime(Date prefferdCallTime) {
		this.prefferdCallTime = prefferdCallTime;
	}
	public Long getAssetId() {
		return assetId;
	}
	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}
	public Long getAssetTypeId() {
		return assetTypeId;
	}
	public void setAssetTypeId(Long assetTypeId) {
		this.assetTypeId = assetTypeId;
	}
	public String getAssetTypeName() {
		return assetTypeName;
	}
	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}
	public String getAssetDescription() {
		return assetDescription;
	}
	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}
	public String getAssetInstallationAddress() {
		return assetInstallationAddress;
	}
	public void setAssetInstallationAddress(String assetInstallationAddress) {
		this.assetInstallationAddress = assetInstallationAddress;
	}
	public String getAssetLat() {
		return assetLat;
	}
	public void setAssetLat(String assetLat) {
		this.assetLat = assetLat;
	}
	public String getAssetLong() {
		return assetLong;
	}
	public void setAssetLong(String assetLong) {
		this.assetLong = assetLong;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerTittle() {
		return customerTittle;
	}
	public void setCustomerTittle(String customerTittle) {
		this.customerTittle = customerTittle;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerMiddletName() {
		return customerMiddletName;
	}
	public void setCustomerMiddletName(String customerMiddletName) {
		this.customerMiddletName = customerMiddletName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}
	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}
	public String getCustomerAternateMobileNumber() {
		return customerAternateMobileNumber;
	}
	public void setCustomerAternateMobileNumber(String customerAternateMobileNumber) {
		this.customerAternateMobileNumber = customerAternateMobileNumber;
	}
	public String getCustomerOfficeNumber() {
		return customerOfficeNumber;
	}
	public void setCustomerOfficeNumber(String customerOfficeNumber) {
		this.customerOfficeNumber = customerOfficeNumber;
	}
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	public String getCustomerAternateEmailId() {
		return customerAternateEmailId;
	}
	public void setCustomerAternateEmailId(String customerAternateEmailId) {
		this.customerAternateEmailId = customerAternateEmailId;
	}
	public String getCustomerCommunicationAddress() {
		return customerCommunicationAddress;
	}
	public void setCustomerCommunicationAddress(String customerCommunicationAddress) {
		this.customerCommunicationAddress = customerCommunicationAddress;
	}


	@Override
	public String toString() {
		return "TicketDetails{" +
				"ticketId=" + ticketId +
				", ticketNo='" + ticketNo + '\'' +
				", ticketDescription='" + ticketDescription + '\'' +
				", currentAssigneeId=" + currentAssigneeId +
				", currentAssigneeName='" + currentAssigneeName + '\'' +
				", ticketStatus=" + ticketStatus +
				", ticketCreatedTime=" + ticketCreatedTime +
				", ticketTypeId=" + ticketTypeId +
				", ticketTypeName='" + ticketTypeName + '\'' +
				", ticketSource='" + ticketSource + '\'' +
				", prefferdCallTime=" + prefferdCallTime +
				", assetId=" + assetId +
				", assetTypeId=" + assetTypeId +
				", assetTypeName='" + assetTypeName + '\'' +
				", assetDescription='" + assetDescription + '\'' +
				", assetInstallationAddress='" + assetInstallationAddress + '\'' +
				", assetLat='" + assetLat + '\'' +
				", assetLong='" + assetLong + '\'' +
				", customerId=" + customerId +
				", customerTittle='" + customerTittle + '\'' +
				", customerFirstName='" + customerFirstName + '\'' +
				", customerMiddletName='" + customerMiddletName + '\'' +
				", customerLastName='" + customerLastName + '\'' +
				", customerMobileNumber='" + customerMobileNumber + '\'' +
				", customerAternateMobileNumber='" + customerAternateMobileNumber + '\'' +
				", customerOfficeNumber='" + customerOfficeNumber + '\'' +
				", customerEmailId='" + customerEmailId + '\'' +
				", customerAternateEmailId='" + customerAternateEmailId + '\'' +
				", customerCommunicationAddress='" + customerCommunicationAddress + '\'' +
				'}';
	}
}
