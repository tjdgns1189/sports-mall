package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.OrdersProductJoinVO;
import edu.spring.mall.domain.OrdersVO;

public interface OrderService {
	
	int create (OrdersVO vo) throws Exception;
	
	List<OrdersVO> readOrder(String memberId) throws Exception;
   
	List<OrdersProductJoinVO> read(String memberId) throws Exception;
	OrdersVO read(int orderId) throws Exception;
	
	int update(OrdersVO vo) throws Exception;
	
	int delete(int orderId) throws Exception;
	
	List<OrdersVO> readRefund(String orderState) throws Exception;

}
