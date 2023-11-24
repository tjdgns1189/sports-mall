package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.ProductQnaJoinReplyVO;
import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface ProductQnaService {
	int create(ProductQnaVO vo) throws Exception;
	List<ProductQnaVO> read();
	List<ProductQnaJoinReplyVO> read(int productId, PageCriteria criteria);
	List<ProductQnaVO> read(String memberId);
	int count(int prdQnaId);
	ProductQnaVO readDetail(int prdQnaId);
	int update(ProductQnaVO vo);
	int delete(int prdQnaId);
	int getTotalCounts(int productId);


}
