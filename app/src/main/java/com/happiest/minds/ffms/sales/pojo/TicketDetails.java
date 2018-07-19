/**
 * 
 */
package com.happiest.minds.ffms.sales.pojo;

import java.util.Date;
import java.util.List;
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
	private String preferredCallTime;

	private Long assetId;
	private Long assetTypeId;
	private String assetTypeName;
	private String assetDescription;
	private String assetInstallationAddress;
	private String assetLat;
	private String assetLong;

	private Long customerId;
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
	private Long cityId;
	private String cityName;
	private Long branchId;
	private String branchName;
	private Long areaId;
	private String areaName;
	private List<ActivityVo> activities;
	private String rejectionReason;

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

	public String getPreferredCallTime() {
		return preferredCallTime;
	}

	public void setPreferredCallTime(String preferredCallTime) {
		this.preferredCallTime = preferredCallTime;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<ActivityVo> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityVo> activities) {
		this.activities = activities;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
}
