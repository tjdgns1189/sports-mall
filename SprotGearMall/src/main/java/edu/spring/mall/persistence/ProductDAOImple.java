package edu.spring.mall.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.ProductVO;

@Repository
public class ProductDAOImple implements ProductDAO{
	
	private static final Logger logger =
			LoggerFactory.getLogger(ProductDAOImple.class);
	
	private static final String NAMESPACE =
			"edu.spring.mall.ProductMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(ProductVO vo) {
		logger.info("insert() »£√‚");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

}
