package edu.spring.mall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.ProductQnaReplyVO;
import edu.spring.mall.persistence.ProductQnaReplyDAO;
@Service
public class ProductQnaReplyServiceImple implements ProductQnaReplyService {
	private final Logger logger = LoggerFactory.getLogger(ProductQnaReplyServiceImple.class);
	
	
	@Autowired
	private ProductQnaReplyDAO dao;

	@Override
	public int create(ProductQnaReplyVO vo) {
		logger.info("create 호출");
	    vo.setPqrContent(vo.getPqrContent().replace("\n", "<br>"));	
		return dao.insert(vo);
	}

	@Override
	public int update(ProductQnaReplyVO vo) {
		logger.info("update 호출");
		return dao.update(vo);
	}

	@Override
	public int delete(ProductQnaReplyVO vo) {
		logger.info("delete");
		return dao.delete(vo);
	}

}
