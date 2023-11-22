package edu.spring.mall.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.OrdersProductJoinVO;
import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.persistence.OrdersDAO;
@Service
public class OrderServiceImple implements OrderService {
	
	@Autowired
	private OrdersDAO orderDAO;
	
	@Autowired
	private ProductService product;
	
	@Autowired
	private ReviewService review;

	@Override
	public List<OrdersProductJoinVO> read(String memberId) {
		List<OrdersVO> orders = orderDAO.select(memberId);
		List<OrdersProductJoinVO> orderList = new ArrayList<>();
		
		for(OrdersVO order : orders) {
			Map<String, Object> map = product.readProductById(order.getProductId());
			ProductVO pro = (ProductVO) map.get("product");
            OrdersProductJoinVO join = new OrdersProductJoinVO();
            join.setOrder(order);
            join.setProduct(pro);
            join.setHasReview(orderDAO.hasReview(order.getOrderId()));
            orderList.add(join);
		}
		
		return orderList;
	}

}
