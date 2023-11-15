package edu.spring.mall.domain;

import java.util.Date;

public class ProductQnaVO {
	private int prdQnaId;
	private String prdQnaContent;
	private String prdQnaCategory;
	private int prdQnaSeret;
	private String memberId;
	private int productId;
	private Date prdQnaCreatedDate;
	public int getPrdQnaId() {
		return prdQnaId;
	}
	public void setPrdQnaId(int prdQnaId) {
		this.prdQnaId = prdQnaId;
	}
	public String getPrdQnaContent() {
		return prdQnaContent;
	}
	public void setPrdQnaContent(String prdQnaContent) {
		this.prdQnaContent = prdQnaContent;
	}
	public String getPrdQnaCategory() {
		return prdQnaCategory;
	}
	public void setPrdQnaCategory(String prdQnaCategory) {
		this.prdQnaCategory = prdQnaCategory;
	}
	public int getPrdQnaSeret() {
		return prdQnaSeret;
	}
	public void setPrdQnaSeret(int prdQnaSeret) {
		this.prdQnaSeret = prdQnaSeret;
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
	public Date getPrdQnaCreatedDate() {
		return prdQnaCreatedDate;
	}
	public void setPrdQnaCreatedDate(Date prdQnaCreatedDate) {
		this.prdQnaCreatedDate = prdQnaCreatedDate;
	}
	public ProductQnaVO(int prdQnaId, String prdQnaContent, String prdQnaCategory, int prdQnaSeret, String memberId,
			int productId, Date prdQnaCreatedDate) {
		this.prdQnaId = prdQnaId;
		this.prdQnaContent = prdQnaContent;
		this.prdQnaCategory = prdQnaCategory;
		this.prdQnaSeret = prdQnaSeret;
		this.memberId = memberId;
		this.productId = productId;
		this.prdQnaCreatedDate = prdQnaCreatedDate;
	}
	public ProductQnaVO() {}
	@Override
	public String toString() {
		return "ProductQnaVO [prdQnaId=" + prdQnaId + ", prdQnaContent=" + prdQnaContent + ", prdQnaCategory="
				+ prdQnaCategory + ", prdQnaSeret=" + prdQnaSeret + ", memberId=" + memberId + ", productId="
				+ productId + ", prdQnaCreatedDate=" + prdQnaCreatedDate + "]";
	}
	
	
	
	

}
