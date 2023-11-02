package edu.spring.mall.domain;

import java.util.Date;

public class ReviewVO {
	private int reviewId;
	private int productId;
	private String reviewContent;
	private int reviewRating;
	private Date reviewCreatedDate;
	private String memberId;






	public ReviewVO() {}

	

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public Date getReviewCreatedDate() {
		return reviewCreatedDate;
	}

	public void setReviewCreatedDate(Date reviewCreatedDate) {
		this.reviewCreatedDate = reviewCreatedDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	@Override
	public String toString() {
		return "ReviewVO [reviewId=" + reviewId + ", productId=" + productId + ", reviewContent=" + reviewContent
				+ ", reviewRating=" + reviewRating + ", reviewCreatedDate=" + reviewCreatedDate + ", memberId="
				+ memberId + "]";
	}



	public ReviewVO(int reviewId, int productId, String reviewContent, int reviewRating, Date reviewCreatedDate,
			String memberId) {
		this.reviewId = reviewId;
		this.productId = productId;
		this.reviewContent = reviewContent;
		this.reviewRating = reviewRating;
		this.reviewCreatedDate = reviewCreatedDate;
		this.memberId = memberId;
	}



	public String getReviewContent() {
		return reviewContent;
	}



	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	
}
