package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.CartVO;

public interface CartDAO {
	int insert(CartVO vo);
	List<CartVO> select(String memberId);
	CartVO select(int cartId);
	int delete(int cartId);
	int update(CartVO vo);
}
