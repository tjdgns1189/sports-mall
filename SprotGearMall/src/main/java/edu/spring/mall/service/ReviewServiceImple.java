package edu.spring.mall.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.domain.ReviewProductJoinVO;
import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.persistence.ReviewDAO;
@Service
public class ReviewServiceImple implements ReviewService {
	private Logger logger = LoggerFactory.getLogger(ReviewServiceImple.class);
	
	@Autowired
	private ReviewDAO dao;
	
	@Autowired
	private ProductService productService;
	
	@Transactional
	@Override
	public int create(ReviewVO vo) throws Exception {
		logger.info("create 호출");
		int result = dao.insert(vo);
		return result;
	}

	@Override
	public List<ReviewVO> read(Principal principal) throws Exception {
		logger.info("read 호출");
		String memberId = "";
		if(principal != null) {
			memberId = principal.getName();
		}
		List<ReviewVO> list = dao.select(memberId);
		return list;
	}

	@Override
	public int update(ReviewVO vo) throws Exception {
		logger.info("update 호출");
		logger.info("reviewId : " + vo.getReviewId());
		logger.info("리뷰 내용 : "+ vo.getReviewContent());
		logger.info("별점 " + vo.getReviewRating());
		int result = dao.update(vo);
		return result;
	}

	@Override
	public int delete(int reviewId) throws Exception {
		logger.info("delete 호출");
		int result = dao.delete(reviewId);
		System.out.println("result = " + result);
		return result;
	}

	@Override
	public List<ReviewProductJoinVO> read(String memberId) throws Exception {
		logger.info("read호출");
		List<ReviewVO> list = dao.select(memberId);
		 List<ReviewProductJoinVO> reviewProduct = new ArrayList<>();
		 for(ReviewVO review : list) {
			 ProductVO vo = productService.read(review.getProductId());
			 ReviewProductJoinVO join = new ReviewProductJoinVO(vo, review);
			 reviewProduct.add(join);
		 }
	
		return reviewProduct;
	}

	@Override
	public ReviewVO read(int reviewId) throws Exception{
		logger.info("read(reviewId ) 호출");
		ReviewVO review = dao.select(reviewId);
		return review;
	}
	
	

}
