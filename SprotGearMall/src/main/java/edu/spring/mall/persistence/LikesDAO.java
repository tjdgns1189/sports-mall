package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.LikesVO;

public interface LikesDAO {
	int insert(LikesVO vo);
	int select(LikesVO vo);
	List<Integer> selectUserLiked(String memberId);
	int delete(LikesVO vo);
}
