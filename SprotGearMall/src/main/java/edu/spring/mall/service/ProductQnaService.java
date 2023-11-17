package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface ProductQnaService {
	int create(ProductQnaVO vo);
	List<ProductQnaVO> read();
	List<ProductQnaVO> read(int productId, PageCriteria criteria);
	List<ProductQnaVO> read(String memberId);
	ProductQnaVO readDetail(int prdQnaId);
	int update(ProductQnaVO vo);
	int delete(int prdQnaId);
	int getTotalCounts(int productId);


}
