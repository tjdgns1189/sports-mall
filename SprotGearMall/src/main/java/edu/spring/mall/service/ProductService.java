package edu.spring.mall.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.spring.mall.domain.CartProductJoinVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface ProductService {
	int create(ProductVO vo, MultipartFile file) throws IOException;
	List<ProductVO> read(PageCriteria criteria);
	ProductVO read(int productId);
	Map<String, Object> readProductById(int productId);
	int update(ProductVO vo);
	int softDelete(ProductVO vo);
	int delete(int productId);
	int getTotalCounts();
	List<ProductVO> readBySearchText(String serchText,PageCriteria criteria);
	List<ProductVO> searchLikesCount(String serchText,PageCriteria criteria);
	List<ProductVO> searchRating(String serchText,PageCriteria criteria);
	List<ProductVO> searchReviewCount(String serchText,PageCriteria criteria);
	
}