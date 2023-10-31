package edu.spring.mall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.LikesVO;

@Repository
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
	public int select(LikesVO vo) {
		logger.info("좋아요 눌렀는지 확인하는거");
		return sqlSession.selectOne(NAMESPACE + ".checkByLiked", vo);
	}
	
	@Override
	public List<Integer> selectUserLiked(String memberId) {
		logger.info("selectUserLiked 호출 member");
		return sqlSession.selectList(NAMESPACE + ".select" , memberId);
	}

	@Override
	public int delete(LikesVO vo) {
		logger.info("delete 호출");
		return sqlSession.delete(NAMESPACE + ".delete", vo);
	}

	



	

}
