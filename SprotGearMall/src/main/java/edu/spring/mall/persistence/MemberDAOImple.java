package edu.spring.mall.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.spring.mall.domain.MemberVO;

public class MemberDAOImple implements MemberDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImple.class);
	private static final String NAMESPACE = "edu.spring.mall.MemberMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(MemberVO vo) {
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

}
