package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.domain.ProductVO;

public interface ProductQnaDAO {
	int insert(ProductQnaVO vo);
	List<ProductQnaVO> select();
	List<ProductQnaVO> select(int productId);
	ProductVO selectDetail(int prdQnaId);
	int update(int prdQnaId, ProductQnaVO vo);
	int delete(int prdQnaId);
	

}
