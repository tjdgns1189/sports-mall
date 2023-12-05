package edu.spring.mall.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.OrdersVO;

@Repository
public class OrdersDAOImple implements OrdersDAO {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(OrdersDAOImple.class);
	
	private static final String NAMESPACE = 
			"edu.spring.mall.OrdersMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(OrdersVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<OrdersVO> select(String memberId) {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_by_member_id", memberId);
	}

	@Override
	public int delete(String memberId, int productId) {
		logger.info("delete() 호출");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("memberId", memberId);
		args.put("productId", productId);
		return sqlSession.delete(NAMESPACE + ".delete", args);
	}

	@Override
	public int delete(int orderId) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete_by_order_id", orderId);
	}

	@Override
	public OrdersVO select(int orderId) {
		logger.info("select(orderId) 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_by_order_Id", + orderId);
	}
	

	@Override
	public boolean hasReview(int orderId) {
        Integer count = sqlSession.selectOne("edu.spring.mall.ReviewMapper.count_Review", orderId);
        return count != null && count > 0;
	}

	@Override
	public int update(OrdersVO vo) {
		logger.info("update 호출");
		return sqlSession.update(NAMESPACE + ".refund_request", vo);
	}

	@Override
	public List<OrdersVO> selectRefund(String orderState) {
		logger.info("selectRefund 호출");
		return sqlSession.selectList(NAMESPACE + ".select_by_refund", orderState);
	}


}
