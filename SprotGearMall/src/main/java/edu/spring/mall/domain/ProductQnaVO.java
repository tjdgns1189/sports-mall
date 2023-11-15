package edu.spring.mall.domain;

import java.util.Date;

public class ProductQnaVO {
	private int prdQnaId;
	private String prdQnaContent;
	private String prdQnaCategory;
	private int prdQnaSecret;
	private String memberId;
	private int productId;
	private Date prdQnaCreatedDate;
	private String prdQnaState;
	

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

	public ProductQnaVO() {}
	public String getPrdQnaState() {
		return prdQnaState;
	}
	public void setPrdQnaState(String prdQnaState) {
		this.prdQnaState = prdQnaState;
	}
	public int getPrdQnaSecret() {
		return prdQnaSecret;
	}
	public void setPrdQnaSecret(int prdQnaSecret) {
		this.prdQnaSecret = prdQnaSecret;
	}
	@Override
	public String toString() {
		return "ProductQnaVO [prdQnaId=" + prdQnaId + ", prdQnaContent=" + prdQnaContent + ", prdQnaCategory="
				+ prdQnaCategory + ", prdQnaSecret=" + prdQnaSecret + ", memberId=" + memberId + ", productId="
				+ productId + ", prdQnaCreatedDate=" + prdQnaCreatedDate + ", prdQnaState=" + prdQnaState + "]";
	}
	public ProductQnaVO(int prdQnaId, String prdQnaContent, String prdQnaCategory, int prdQnaSecret, String memberId,
			int productId, Date prdQnaCreatedDate, String prdQnaState) {
		this.prdQnaId = prdQnaId;
		this.prdQnaContent = prdQnaContent;
		this.prdQnaCategory = prdQnaCategory;
		this.prdQnaSecret = prdQnaSecret;
		this.memberId = memberId;
		this.productId = productId;
		this.prdQnaCreatedDate = prdQnaCreatedDate;
		this.prdQnaState = prdQnaState;
	}




	
	
	
	

}
