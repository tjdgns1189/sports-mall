package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.OrdersProductJoinVO;

public interface OrderService {
   
	List<OrdersProductJoinVO> read(String memberId) throws Exception;

}
