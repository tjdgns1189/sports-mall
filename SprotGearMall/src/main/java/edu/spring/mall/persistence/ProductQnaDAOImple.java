package edu.spring.mall.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.ProductQnaJoinReplyVO;
import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.pageutil.PageCriteria;
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
	public List<ProductQnaJoinReplyVO> select(int productId, PageCriteria criteria) {
		logger.info("select 호출(디테일)");
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("criteria", criteria);
		return sqlSession.selectList(NAMESPACE + ".paging", params);
	}
	//유저 개인 문의
	@Override
	public List<ProductQnaVO> select(String memberId) {
		logger.info("select호출(유저 문의)");
		return sqlSession.selectList(NAMESPACE + ".select_by_memberId" , memberId);
	}
	
	//수정시 값 가져올거
	@Override
	public ProductQnaVO selectDetail(int prdQnaId) {
		logger.info("select 호출(업데이트용)");
		return sqlSession.selectOne(NAMESPACE + ".select_by_qnaId", prdQnaId);
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
	@Override
	public int getTotalCount(int productId) {
		logger.info("getTotalCount 호출");
		return sqlSession.selectOne(NAMESPACE+ ".total_count", productId);
	}
	@Override
	public int count(int prdQnaId) {
		logger.info("count 호출");
		return sqlSession.selectOne(NAMESPACE + ".count",prdQnaId);
	}


}
