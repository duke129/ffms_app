/**
 * 
 */
package com.happiest.minds.ffms.sales.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author kiran
 *
 */
public class OrderActivityUpdate implements Serializable{
	
	private List<OrderVo> ordersVo;
	
	private String comments;
	
	private Long ticketId;

	public List<OrderVo> getOrdersVo() {
		return ordersVo;
	}

	public void setOrdersVo(List<OrderVo> ordersVo) {
		this.ordersVo = ordersVo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	@Override
	public String toString() {
		return "OrderActivityUpdate [ordersVo=" + ordersVo + ", comments=" + comments + ", ticketId=" + ticketId + "]";
	}

	
	
	
	

}
