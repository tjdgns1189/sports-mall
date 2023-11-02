package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.ReviewVO;

public interface ReviewDAO {
	int insert(ReviewVO vo);
	List<ReviewVO> select(String memberId);
	int update(ReviewVO vo);
	int delete(int reviewId);

}
