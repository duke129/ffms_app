/**
 * 
 */
package com.happiest.minds.ffms.sales.pojo;

/**
 * @author kiran
 *
 */
public class APIResponse {
	
	private Integer statusId;
	private String statusMessage;
	private Object data;
	
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "APIResponse [statusId=" + statusId + ", statusMessage=" + statusMessage + ", data=" + data + "]";
	}
	
	

}
