/**
 * 
 */
package com.happiest.minds.ffms.sales.pojo;

import java.math.BigInteger;

/**
 * @author kiran
 *
 */
public class CustomerVo {
	
	
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
	private Long cityId;
	private Long branchId;
	private Long areaId;
	private String type;
	private BigInteger count;
	
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
	public AddressVo getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(AddressVo currentAddress) {
		this.currentAddress = currentAddress;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
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
	
	
	public BigInteger getCount() {
		return count;
	}
	public void setCount(BigInteger count) {
		this.count = count;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "CustomerVo [title=" + title + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", mobileNumber=" + mobileNumber + ", alternateMobileNumber=" + alternateMobileNumber
				+ ", officeNumber=" + officeNumber + ", emailId=" + emailId + ", alternateEmailId=" + alternateEmailId
				+ ", communicationAddress=" + communicationAddress + ", currentAddress=" + currentAddress + ", cityId="
				+ cityId + ", branchId=" + branchId + ", areaId=" + areaId + "]";
	}
	

	
	
	
	

}
