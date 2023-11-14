package edu.spring.mall.domain;

import java.util.Date;
import java.util.List;

public class CartVO {
	private int cartId;
	private String memberId;
	private int productId;
	private int productPrice;
	private int productQuantity;
	private Date cartCreatedDate;
	private List<CartVO> cartVoList;
	
	public List<CartVO> getCartVoList(){
		return cartVoList;
	}
	
	public void setCartVoList(List<CartVO> cartVoList) {
		this.cartVoList = cartVoList;
	}
	
	public CartVO() {}

	public CartVO(int cartId, String memberId, int productId, int productPrice, int productQuantity,
			Date cartCreatedDate) {
		super();
		this.cartId = cartId;
		this.memberId = memberId;
		this.productId = productId;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.cartCreatedDate = cartCreatedDate;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
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

	public Date getCartCreatedDate() {
		return cartCreatedDate;
	}

	public void setCartCreatedDate(Date cartCreatedDate) {
		this.cartCreatedDate = cartCreatedDate;
	}

	@Override
	public String toString() {
		return "cartVO [cartId=" + cartId + ", memberId=" + memberId + ", productId=" + productId + ", productPrice="
				+ productPrice + ", productQuantity=" + productQuantity + ", cartCreatedDate=" + cartCreatedDate + "]";
	}
	

}

