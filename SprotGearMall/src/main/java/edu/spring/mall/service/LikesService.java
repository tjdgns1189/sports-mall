package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.ProductVO;

public interface LikesService {
	List<ProductVO> read(String memberId);

}
