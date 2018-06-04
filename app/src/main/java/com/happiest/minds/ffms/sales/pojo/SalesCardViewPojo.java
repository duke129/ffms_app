package com.happiest.minds.ffms.sales.pojo;

public class SalesCardViewPojo {

    private String prospectNo;
    private String customerName;
    private String mobileNo;
    private String createdDate;
    private String address;
    private String etr;

    public String getProspectNo() {
        return prospectNo;
    }

    public void setProspectNo(String prospectNo) {
        this.prospectNo = prospectNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEtr() {
        return etr;
    }

    public void setEtr(String etr) {
        this.etr = etr;
    }

    @Override
    public String toString() {
        return "SalesCardViewPojo{" +
                "prospectNo='" + prospectNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", address='" + address + '\'' +
                ", etr='" + etr + '\'' +
                '}';
    }
}
