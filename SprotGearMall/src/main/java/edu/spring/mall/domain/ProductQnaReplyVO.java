package edu.spring.mall.domain;

import java.util.Date;

public class ProductQnaReplyVO {
	private int pqrId;
	private String pqrContent;
	private int prdQnaId;
	private String memberId;
	private int productId;
	private Date pqrCreatedDate;
	
	public int getPqrId() {
		return pqrId;
	}
	public void setPqrId(int pqrId) {
		this.pqrId = pqrId;
	}
	public String getPqrContent() {
		return pqrContent;
	}
	public void setPqrContent(String pqrContent) {
		this.pqrContent = pqrContent;
	}
	public int getPrdQnaId() {
		return prdQnaId;
	}
	public void setPrdQnaId(int prdQnaId) {
		this.prdQnaId = prdQnaId;
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
	public Date getPqrCreatedDate() {
		return pqrCreatedDate;
	}
	public void setPqrCreatedDate(Date pqrCreatedDate) {
		this.pqrCreatedDate = pqrCreatedDate;
	}
	public ProductQnaReplyVO(int pqrId, String pqrContent, int prdQnaId, String memberId, int productId,
			Date pqrCreatedDate) {
		this.pqrId = pqrId;
		this.pqrContent = pqrContent;
		this.prdQnaId = prdQnaId;
		this.memberId = memberId;
		this.productId = productId;
		this.pqrCreatedDate = pqrCreatedDate;
	}
	@Override
	public String toString() {
		return "ProductQnaReplyVO [pqrId=" + pqrId + ", pqrContent=" + pqrContent + ", prdQnaId=" + prdQnaId
				+ ", memberId=" + memberId + ", productId=" + productId + ", pqrCreatedDate=" + pqrCreatedDate + "]";
	}
	public ProductQnaReplyVO(String pqrContent, int prdQnaId, String memberId, int productId) {
		this.pqrContent = pqrContent;
		this.prdQnaId = prdQnaId;
		this.memberId = memberId;
		this.productId = productId;
	}
	public ProductQnaReplyVO() {
	}
	
	
	

}
