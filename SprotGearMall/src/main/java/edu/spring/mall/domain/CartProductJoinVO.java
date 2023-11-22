package edu.spring.mall.domain;

public class CartProductJoinVO {
	private CartVO cart;
	private ProductVO product;
	
	public CartProductJoinVO() {}

	public CartProductJoinVO(CartVO cart, ProductVO product) {
		super();
		this.cart = cart;
		this.product = product;
	}

	public CartVO getCart() {
		return cart;
	}

	public void setCart(CartVO cart) {
		this.cart = cart;
	}

	public ProductVO getProduct() {
		return product;
	}

	public void setProduct(ProductVO product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CartProductJoinVO [cart=" + cart + ", product=" + product + "]";
	}
	
	
	
	
}
