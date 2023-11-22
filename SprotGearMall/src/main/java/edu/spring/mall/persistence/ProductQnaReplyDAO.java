package edu.spring.mall.persistence;

import edu.spring.mall.domain.ProductQnaReplyVO;

public interface ProductQnaReplyDAO {
	int insert(ProductQnaReplyVO vo);
	int update(ProductQnaReplyVO vo);
	int delete(ProductQnaReplyVO vo);

}
