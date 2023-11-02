package edu.spring.mall.domain;

import java.util.Date;

public class OrdersVO {
	private int orderId;
	private String memberId;
	private int productId;
	private int productPrice;
	private int productQuantity;
	private Date orderCreatedDate;
	
	
	public OrdersVO() {}


	public OrdersVO(int orderId, String memberId, int productId, int productPrice, int productQuantity,
			Date orderCreatedDate) {
		this.orderId = orderId;
		this.memberId = memberId;
		this.productId = productId;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.orderCreatedDate = orderCreatedDate;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}


	public int getProductQuantity() {
		return productQuantity;
	}


	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}


	public Date getOrderCreatedDate() {
		return orderCreatedDate;
	}


	public void setOrderCreatedDate(Date orderCreatedDate) {
		this.orderCreatedDate = orderCreatedDate;
	}


	@Override
	public String toString() {
		return "OrderVO [orderId=" + orderId + ", memberId=" + memberId + ", productId=" + productId + ", productPrice="
				+ productPrice + ", productQuantity=" + productQuantity + ", orderCreatedDate=" + orderCreatedDate
				+ "]";
	}
	
	
	
}
