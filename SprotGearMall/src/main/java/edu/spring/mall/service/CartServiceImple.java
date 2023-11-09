package edu.spring.mall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.CartProductJoinVO;
import edu.spring.mall.domain.CartVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.persistence.CartDAO;

@Service
public class CartServiceImple implements CartService{
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private ProductService product;

	@Override
	public List<CartProductJoinVO> read(String memberId) {
		List<CartVO> cart = cartDAO.select(memberId);
		List<CartProductJoinVO> cartList = new ArrayList<>();
		
		for(CartVO car : cart) {
			ProductVO pro = product.read(car.getProductId());
			CartProductJoinVO join = new CartProductJoinVO();
			join.setCart(car);
			join.setProduct(pro);
			cartList.add(join);
		}
		return cartList;
	}

}
