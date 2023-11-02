package edu.spring.mall.service;

import java.security.Principal;
import java.util.List;

import edu.spring.mall.domain.ReviewVO;

public interface ReviewService {
	int create(ReviewVO vo) throws Exception;
	List<ReviewVO> read(Principal principal) throws Exception;
	int update(ReviewVO vo)throws Exception;
	int delete(int reviewId) throws Exception;
}
