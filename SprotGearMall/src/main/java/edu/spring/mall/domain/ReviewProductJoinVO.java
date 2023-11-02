package edu.spring.mall.domain;

public class ReviewProductJoinVO {
	private ProductVO product;
	private ReviewVO review;
	
	public ReviewProductJoinVO() {
		super();
	}
	public ReviewProductJoinVO(ProductVO product, ReviewVO review) {
		super();
		this.product = product;
		this.review = review;
	}
	@Override
	public String toString() {
		return "ReviewProductJoinVO [product=" + product + ", review=" + review + "]";
	}
	public ProductVO getProduct() {
		return product;
	}
	public void setProduct(ProductVO product) {
		this.product = product;
	}
	public ReviewVO getreview() {
		return review;
	}
	public void setReview(ReviewVO review) {
		this.review = review;
	}
	
	
	
	
}

