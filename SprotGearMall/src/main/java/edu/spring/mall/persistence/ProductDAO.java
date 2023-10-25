package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface ProductDAO {
	int insert(ProductVO vo);
	List<ProductVO> select();
	ProductVO select(String productName);
	int update(ProductVO vo);
	int delete(String productName);
	List<ProductVO> select(PageCriteria criteria);
	int getTotalCounts();
	List<ProductVO> selectPaging(String productName);
	ProductVO select(int productId);
	
}