package edu.spring.mall.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.spring.mall.domain.LikesVO;

public class LikesDAOImple implements LikesDAO {
	
	private final Logger logger = LoggerFactory.getLogger(LikesDAOImple.class);
	private static final String NAMESPACE ="edu.spring.mall.LikesMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(LikesVO vo) {
		logger.info("inser 호출");
		return sqlSession.insert(NAMESPACE + ".insert" , vo);
	}



	@Override
	public int delete(LikesVO vo) {
		logger.info("delete 호출");
		return sqlSession.delete(NAMESPACE + ".delete", vo);
	}

}
