package edu.spring.mall.persistence;

import edu.spring.mall.domain.LikesVO;

public interface LikesDAO {
	int insert(LikesVO vo);
	int delete(LikesVO vo);
}
