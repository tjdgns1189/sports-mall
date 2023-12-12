package edu.spring.mall.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.OrdersProductJoinVO;
import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.persistence.OrdersDAO;

@Service
public class OrderServiceImple implements OrderService {
	private final Logger logger = LoggerFactory.getLogger(OrderServiceImple.class);

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

		for (OrdersVO order : orders) {
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

	@Override
	public int update(OrdersVO vo) throws Exception {
		logger.info("update 호출");
		return orderDAO.update(vo);
	}

	@Override
	public List<OrdersVO> readRefund(String orderState) throws Exception {
		logger.info("readRefund호출");
		return orderDAO.selectRefund(orderState);
	}

	@Override
	public int create(OrdersVO vo) throws Exception {
		logger.info("create 호출");
		return orderDAO.insert(vo);
	}

	@Override
	public List<OrdersVO> readOrder(String memberId) throws Exception {
		logger.info("readOrder 호출");
		return orderDAO.select(memberId);
	}

	@Override
	public int delete(int orderId) throws Exception {
		logger.info("delete 호출");
		return orderDAO.delete(orderId);
	}

	@Override
	public OrdersVO read(int orderId) throws Exception {
		logger.info("read 호출");
		return orderDAO.select(orderId);
	}

}
