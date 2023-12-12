package edu.spring.mall.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.CartProductJoinVO;
import edu.spring.mall.domain.CartVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.persistence.CartDAO;

@Service
public class CartServiceImple implements CartService{
	private static final Logger logger =
			LoggerFactory.getLogger(CartServiceImple.class);
	
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

	@Override
	public int delete(int cartId) {
		logger.info("delete() 호출 : cartId = " + cartId);
		return cartDAO.delete(cartId);
	}

	@Override
	public int create(CartVO vo) throws Exception {
		logger.info("create 호출");
		return cartDAO.insert(vo);
	}

	@Override
	public int update(CartVO vo) throws Exception {
		logger.info("update 호출");
		return cartDAO.update(vo);
	}

	@Override
	public CartVO readProductId(int productId, String memberId) {
		logger.info("readProductId 호출");
		return cartDAO.selectByProductIdOne(productId, memberId);
	}


}
