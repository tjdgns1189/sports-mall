package edu.spring.mall.domain;

import java.util.Date;

public class ProductReplyVO {
	private int productReplyId;
	private int productId;
	private String memberId;
	private String productReplyContent;
	private Date productReplyCreatedDate;
	
	
	public ProductReplyVO() {}


	public ProductReplyVO(int productReplyId, int productId, String memberId, String productReplyContent,
			Date productReplyCreatedDate) {
		super();
		this.productReplyId = productReplyId;
		this.productId = productId;
		this.memberId = memberId;
		this.productReplyContent = productReplyContent;
		this.productReplyCreatedDate = productReplyCreatedDate;
	}


	public int getProductReplyId() {
		return productReplyId;
	}


	public void setProductReplyId(int productReplyId) {
		this.productReplyId = productReplyId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getProductReplyContent() {
		return productReplyContent;
	}


	public void setProductReplyContent(String productReplyContent) {
		this.productReplyContent = productReplyContent;
	}


	public Date getProductReplyCreatedDate() {
		return productReplyCreatedDate;
	}


	public void setProductReplyCreatedDate(Date productReplyCreatedDate) {
		this.productReplyCreatedDate = productReplyCreatedDate;
	}


	@Override
	public String toString() {
		return "ProductReplyVO [productReplyId=" + productReplyId + ", productId=" + productId + ", memberId="
				+ memberId + ", productReplyContent=" + productReplyContent + ", productReplyCreatedDate="
				+ productReplyCreatedDate + "]";
	}
	
	
	

	
	
}
