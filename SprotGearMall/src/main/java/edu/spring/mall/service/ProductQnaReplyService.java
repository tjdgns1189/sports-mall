package edu.spring.mall.service;

import edu.spring.mall.domain.ProductQnaReplyVO;

public interface ProductQnaReplyService {
	int create(ProductQnaReplyVO vo, String authorId) throws Exception;
	int update(ProductQnaReplyVO vo);
	int delete(ProductQnaReplyVO vo);

}
