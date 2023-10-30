package edu.spring.mall.domain;

public class LikesVO {
	private int likes;
	private String memberId;
	private int productId;
	
	public LikesVO(int likes, String memberId, int productId) {
		this.likes = likes;
		this.memberId = memberId;
		this.productId = productId;
	}


	public LikesVO() {}



	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
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

	@Override
	public String toString() {
		return "LikesVO [likes=" + likes + ", memberId=" + memberId + ", productId=" + productId + "]";
	}
	
	
	
	
}
