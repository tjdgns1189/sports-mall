package edu.spring.mall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.QnaBoardVO;

@Repository
public class QnaBoardDAOImple implements QnaBoardDAO{
	
	private static final Logger logger = 
			LoggerFactory.getLogger(QnaBoardDAOImple.class);
	
	private static final String NAMESPACE =
			"edu.spring.mall.OrdersMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(QnaBoardVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<QnaBoardVO> select() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	}

}
