package edu.spring.mall.service;

import java.util.List;
import java.util.Map;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface ProductService {
	int create(ProductVO vo);
	List<ProductVO> read(PageCriteria criteria);
	ProductVO read(int productId);
	Map<String, Object> readProductById(int productId);
	int update(ProductVO vo);
	int delete(String productName);
	int getTotalCounts();
}