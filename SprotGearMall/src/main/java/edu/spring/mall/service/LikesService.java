package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.LikesVO;
import edu.spring.mall.domain.ProductVO;

public interface LikesService {
	int create(LikesVO vo) throws Exception;
	
	List<ProductVO> read(String memberId);
	
	int count(LikesVO vo);
	
	int delete(LikesVO vo) throws Exception;

}
