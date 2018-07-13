/**
 * 
 */
package com.happiest.minds.ffms.sales.pojo;

import java.io.Serializable;

/**
 * @author kiran
 *
 */
public class OrderVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idOrder;
	private String price;
	private Long productId;
    private int quantity;
    private String productName;
    
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}
	@Override
	public String toString() {
		return "OrderVo [idOrder=" + idOrder + ", price=" + price + ", productId=" + productId + ", quantity="
				+ quantity + ", productName=" + productName + "]";
	}
	
	
	
    
    
    
    

}
