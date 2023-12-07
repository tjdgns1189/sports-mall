package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.CartProductJoinVO;
import edu.spring.mall.domain.CartVO;

public interface CartService {
	int create(CartVO vo) throws Exception;
	int update(CartVO vo) throws Exception;
	int delete(int cartId);
	List<CartProductJoinVO> read(String memberId) throws Exception;
}
