package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.ProductVO;

public interface ProductDAO {
	int insert(ProductVO vo);
	List<ProductVO> select();
	ProductVO select(int productId);
}
