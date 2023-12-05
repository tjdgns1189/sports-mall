package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.OrdersProductJoinVO;
import edu.spring.mall.domain.OrdersVO;

public interface OrderService {
   
	List<OrdersProductJoinVO> read(String memberId) throws Exception;
	
	int update(OrdersVO vo) throws Exception;
	
	List<OrdersVO> readRefund(String orderState) throws Exception;

}
