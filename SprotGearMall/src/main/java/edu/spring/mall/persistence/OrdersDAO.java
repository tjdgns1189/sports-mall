package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.OrdersVO;

public interface OrdersDAO {
	int insert(OrdersVO vo);
	List<OrdersVO> select(String memberId);
<<<<<<< HEAD
	OrdersVO select(int orderId);
=======
>>>>>>> refs/remotes/origin/feature/VO
	int delete(String memberId, int productId);
	int delete(int orderId);
}
