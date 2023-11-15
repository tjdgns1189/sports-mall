package edu.spring.mall.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.persistence.ProductQnaDAO;
@Service
public class ProductQnaServiceImple implements ProductQnaService {
	private final Logger logger = LoggerFactory.getLogger(ProductQnaServiceImple.class);
	
	@Autowired
	private ProductQnaDAO dao;
	
	@Override
	public int create(ProductQnaVO vo) {
		logger.info("create 호출");
		
		return dao.insert(vo);
	}

	@Override
	public List<ProductQnaVO> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductQnaVO> read(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductQnaVO readDetail(int prdQnaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ProductQnaVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int PrdQnaId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
