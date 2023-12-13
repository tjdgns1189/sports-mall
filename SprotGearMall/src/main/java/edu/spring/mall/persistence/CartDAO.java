package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.CartVO;

public interface CartDAO {
	int insert(CartVO vo);
	List<CartVO> select(String memberId);
	CartVO select(int cartId);
	List<CartVO> selectByProductId(int productId);
	CartVO selectByProductIdOne(int productId, String memberId);
	int delete(int cartId);
	int update(CartVO vo);
}
