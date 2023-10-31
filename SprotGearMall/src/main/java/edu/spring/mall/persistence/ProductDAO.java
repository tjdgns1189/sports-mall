package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface ProductDAO {
	int insert(ProductVO vo);
	List<ProductVO> select();
	ProductVO selectByName(String productName);
	ProductVO selectById(int productId);
	List<ProductVO> select(PageCriteria criteria);
	int update(ProductVO vo);
	int delete(String productName);
	int getTotalCounts();
	List<ProductVO> selectPaging(String productName);
	
	
}