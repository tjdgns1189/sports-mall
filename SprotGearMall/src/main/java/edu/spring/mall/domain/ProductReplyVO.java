package edu.spring.mall.domain;

import java.util.Date;

public class ProductReplyVO {
	private int pReplyId;
	private int productId;
	private String memberId;
	private String pReplyContent;
	private Date pReplyCreatedDate;
	
	public ProductReplyVO() {}

	public ProductReplyVO(int pReplyId, int productId, String memberId, String pReplyContent, Date pReplyCreatedDate) {
		super();
		this.pReplyId = pReplyId;
		this.productId = productId;
		this.memberId = memberId;
		this.pReplyContent = pReplyContent;
		this.pReplyCreatedDate = pReplyCreatedDate;
	}

	public int getpReplyId() {
		return pReplyId;
	}

	public void setpReplyId(int pReplyId) {
		this.pReplyId = pReplyId;
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
		return pReplyContent;
	}

	public void setpReplyContent(String pReplyContent) {
		this.pReplyContent = pReplyContent;
	}

	public Date getpReplyCreatedDate() {
		return pReplyCreatedDate;
	}

	public void setpReplyCreatedDate(Date pReplyCreatedDate) {
		this.pReplyCreatedDate = pReplyCreatedDate;
	}

	@Override
	public String toString() {
		return "ProductReplyVO [pReplyId=" + pReplyId + ", productId=" + productId + ", memberId=" + memberId
				+ ", pReplyContent=" + pReplyContent + ", pReplyCreatedDate=" + pReplyCreatedDate + "]";
	}
	
	
	
	
}
