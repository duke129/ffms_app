/**
 * 
 */
package com.happiest.minds.ffms.sales.pojo;

/**
 * @author kiran
 *
 */
public class AddressVo {
	
	private String address1;
	private String address2;
	private String landmark;
	private String latitude;
	private String longitude;
	private String pincode;
	private String apartmentName;
	
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	
	@Override
	public String toString() {
		return "AddressVo [address1=" + address1 + ", address2=" + address2 + ", landmark=" + landmark + ", latitude="
				+ latitude + ", longitude=" + longitude + ", pincode=" + pincode + ", apartmentName=" + apartmentName
				+ "]";
	}
	
	
	
	

}
