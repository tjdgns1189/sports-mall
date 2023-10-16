package edu.spring.mall.persistence;

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
		logger.info("insert() »£√‚");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

}
