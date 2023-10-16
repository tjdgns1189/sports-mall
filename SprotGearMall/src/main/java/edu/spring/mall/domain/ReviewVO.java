package edu.spring.mall.domain;

import java.util.Date;

public class ReviewVO {
	private int reviewId;
	private int productId;
	private String review;
	private int reviewRating;
	private Date reviewCreatedDate;
	private String memberId;
	
	public ReviewVO() {}

	public ReviewVO(int reviewId, int productId, String review, int reviewRating, Date reviewCreatedDate,
			String memberId) {
		super();
		this.reviewId = reviewId;
		this.productId = productId;
		this.review = review;
		this.reviewRating = reviewRating;
		this.reviewCreatedDate = reviewCreatedDate;
		this.memberId = memberId;
	}

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

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
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
		return "ReviewVO [reviewId=" + reviewId + ", productId=" + productId + ", review=" + review + ", reviewRating="
				+ reviewRating + ", reviewCreatedDate=" + reviewCreatedDate + ", memberId=" + memberId + "]";
	}
	
	
	
	
}
