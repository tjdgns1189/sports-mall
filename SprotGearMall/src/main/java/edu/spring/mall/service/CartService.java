package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.CartProductJoinVO;

public interface CartService {
	List<CartProductJoinVO> read(String memberId) throws Exception;
}
