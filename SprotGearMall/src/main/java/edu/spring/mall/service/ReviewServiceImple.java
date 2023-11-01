package edu.spring.mall.service;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.persistence.ReviewDAO;
@Service
public class ReviewServiceImple implements ReviewService {
	private Logger logger = LoggerFactory.getLogger(ReviewServiceImple.class);
	
	@Autowired
	private ReviewDAO dao;

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
		int result = dao.update(vo);
		return result;
	}

	@Override
	public int delete(int reviewId) throws Exception {
		logger.info("delete 호출");
		int result = dao.delete(reviewId);
		return result;
	}

}
