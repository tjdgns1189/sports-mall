package edu.spring.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.persistence.LikesDAO;
import edu.spring.mall.persistence.ProductDAO;
import edu.spring.mall.persistence.ReviewDAO;


@Service
public class ProductServiceImple implements ProductService {
	private static final Logger logger = 
			LoggerFactory.getLogger(ProductServiceImple.class);
	
	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private LikesDAO likes;
	
	@Autowired
	private ReviewDAO reviewDAO;

	@Override
	public int create(ProductVO vo) {
		logger.info("create() 호출 : vo = " + vo.toString()); 
		return dao.insert(vo);
	}

	@Override
	public List<ProductVO> read(PageCriteria criteria) {
		logger.info("read(criteria) 호출");		
		return dao.select(criteria);
	}
	
	@Override
	public Map<String,Object> read(int productId) {
		logger.info("read(productId) 호출 : productId = " + productId);
		Map<String, Object> map = new HashMap<String, Object>();
		ProductVO product = dao.selectById(productId);
		List<ReviewVO> review = reviewDAO.selectProductReview(productId);
		map.put("product", product);
		map.put("review", review);
		return map;
	}

	@Override
	public int update(ProductVO vo) {
		logger.info("update() 호출 : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(String productName) {
		logger.info("delete()  호출: productName = " + productName);
		return dao.delete(productName);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() 호출");
		return dao.getTotalCounts();
	}

}