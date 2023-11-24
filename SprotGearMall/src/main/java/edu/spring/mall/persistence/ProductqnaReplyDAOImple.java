package edu.spring.mall.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.ProductQnaReplyVO;
@Repository
public class ProductqnaReplyDAOImple implements ProductQnaReplyDAO {
	private final Logger logger = LoggerFactory.getLogger(ProductqnaReplyDAOImple.class);
	private final String NAMESPACE = "edu.spring.mall.ProductQnaReply";
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public int insert(ProductQnaReplyVO vo) {
		logger.info("insert 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int update(ProductQnaReplyVO vo) {
		logger.info("update 호출");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(ProductQnaReplyVO vo) {
		logger.info("delete 호출");
		return sqlSession.delete(NAMESPACE + ".delete", vo);
	}

}
