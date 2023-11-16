package edu.spring.mall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.domain.ProductVO;
@Repository
public class ProductQnaDAOImple implements ProductQnaDAO {
	private final Logger logger = LoggerFactory.getLogger(ProductQnaDAOImple.class);
	private final String NAMESPACE = "edu.spring.mall.ProductQnaMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ProductQnaVO vo) {
		logger.info("insert");
		return sqlSession.insert(NAMESPACE + ".insert",vo);
	}
	//관리자페이지에 보여줄거
	@Override
	public List<ProductQnaVO> select() {
		logger.info("select 관리자용");
		return sqlSession.selectList(NAMESPACE + ".select");
	}
	//디테일창에 보여줄거
	@Override
	public List<ProductQnaVO> select(int productId) {
		logger.info("select 호출(디테일)");
		return sqlSession.selectList(NAMESPACE + ".selectDetail", productId);
	}
	//유저 개인 문의
	@Override
	public List<ProductQnaVO> select(String memberId) {
		logger.info("select호출(유저 문의)");
		return sqlSession.selectList(NAMESPACE + ".select_by_memberId" , memberId);
	}

	@Override
	public ProductVO selectDetail(int prdQnaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ProductQnaVO vo) {
		logger.info("update 호출");
		return sqlSession.update(NAMESPACE +".update", vo);
	}

	@Override
	public int delete(int prdQnaId) {
		logger.info("delete 호출");
		return sqlSession.delete(NAMESPACE + ".delete", prdQnaId);
	}


}
