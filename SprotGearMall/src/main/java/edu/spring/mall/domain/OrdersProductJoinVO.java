package edu.spring.mall.domain;

public class OrdersProductJoinVO {
	private OrdersVO order;
	private ProductVO product;
	private boolean hasReview;
	
	public boolean isHasReview() {
		return hasReview;
	}
	public void setHasReview(boolean hasReview) {
		this.hasReview = hasReview;
	}
	public OrdersProductJoinVO(OrdersVO order, ProductVO product) {
	
		this.order = order;
		this.product = product;
	}
	public OrdersVO getOrder() {
		return order;
	}
	public void setOrder(OrdersVO orders) {
		this.order = orders;
	}
	public ProductVO getProduct() {
		return product;
	}
	public void setProduct(ProductVO product) {
		this.product = product;
	}
	public OrdersProductJoinVO() {
	}
	@Override
	public String toString() {
		return "OrdersProductJoinVO [order=" + order + ", product=" + product + "]";
	}
	
}
