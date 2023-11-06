package edu.spring.mall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.ReviewVO;

@Repository
public class ReviewDAOImple implements ReviewDAO{
	private final Logger logger = LoggerFactory.getLogger(ReviewDAOImple.class);
	
	private final String NAMESPACE="edu.spring.mall.ReviewMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ReviewVO vo) {
		logger.info("insert 호출");
		
		return sqlSession.insert(NAMESPACE + ".insert" , vo);
	}

	@Override
	public List<ReviewVO> select(String memberId) {
		logger.info("select 호출");
		return sqlSession.selectList(NAMESPACE + ".select" , memberId);
	}

	@Override
	public int update(ReviewVO vo) {
		logger.info("update 호출");
		return sqlSession.update(NAMESPACE + ".update" , vo);
	}

	@Override
	public int delete(int reviewId) {
		logger.info("delete 호출");
		return sqlSession.delete(NAMESPACE + ".delete" , reviewId);
	}

}
