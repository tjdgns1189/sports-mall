package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface ProductService {
	int create(ProductVO vo);
	List<ProductVO> read(PageCriteria criteria);
	ProductVO read(String ProductName);
	int update(ProductVO vo);
	int delete(String ProductName);
	int getTotalCounts();
}
