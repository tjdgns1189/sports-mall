package edu.spring.mall.domain;

import java.util.List;

public class CartFormVO {
    private List<CartVO> cartList;

    public List<CartVO> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartVO> cartList) {
        this.cartList = cartList;
    }

    @Override
    public String toString() {
        return "CartFormVO [cartList=" + cartList + "]";
    }
}
