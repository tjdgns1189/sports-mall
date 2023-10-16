package edu.spring.mall.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.MemberVO;


@Repository
public class MemberDAOImple implements MemberDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImple.class);
	private static final String NAMESPACE = "edu.spring.mall.MemberMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(MemberVO vo) {
		logger.info("dao insert 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<MemberVO> select() {
		logger.info("select호출");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	}

	@Override
	public int update(MemberVO vo) {
		 	logger.info("update 호출");
		    return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(String memberId) {
		logger.info("delete 호출");
		return sqlSession.delete(NAMESPACE + ".delete", memberId);
	}

}
