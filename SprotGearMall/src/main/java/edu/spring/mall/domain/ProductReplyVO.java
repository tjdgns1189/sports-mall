package edu.spring.mall.domain;

import java.util.Date;

public class ProductReplyVO {
	private int productReplyId;
	private int productId;
	private String memberId;
	private String productReplyContent;
	private Date productReplyCreatedDate;
	
	public ProductReplyVO() {}

	public ProductReplyVO(int pReplyId, int productId, String memberId, String pReplyContent, Date pReplyCreatedDate) {
		super();
		this.productReplyId = pReplyId;
		this.productId = productId;
		this.memberId = memberId;
		this.productReplyContent = pReplyContent;
		this.productReplyCreatedDate = pReplyCreatedDate;
	}

	public int getpReplyId() {
		return productReplyId;
	}

	public void setpReplyId(int pReplyId) {
		this.productReplyId = pReplyId;
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

	public String getpReplyContent() {
		return productReplyContent;
	}

	public void setpReplyContent(String pReplyContent) {
		this.productReplyContent = pReplyContent;
	}

	public Date getpReplyCreatedDate() {
		return productReplyCreatedDate;
	}

	public void setpReplyCreatedDate(Date pReplyCreatedDate) {
		this.productReplyCreatedDate = pReplyCreatedDate;
	}

	@Override
	public String toString() {
		return "ProductReplyVO [productReplyId=" + productReplyId + ", productId=" + productId + ", memberId=" + memberId
				+ ", productReplyContent=" + productReplyContent + ", productReplyCreatedDate=" + productReplyCreatedDate + "]";
	}
	
	
	
	
}
