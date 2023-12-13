package edu.spring.mall.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.spring.mall.domain.CartProductJoinVO;
import edu.spring.mall.domain.CartVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.persistence.CartDAO;
import edu.spring.mall.persistence.LikesDAO;
import edu.spring.mall.persistence.ProductDAO;
import edu.spring.mall.persistence.ReviewDAO;

@Service
public class ProductServiceImple implements ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImple.class);

	@Autowired
	private ProductDAO dao;

	@Autowired
	private LikesDAO likes;

	@Autowired
	private ReviewDAO reviewDAO;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService product;

	@Autowired
	private ImageService imageService;

	@Transactional
	@Override
	public int create(ProductVO vo, MultipartFile file) throws IOException {
		logger.info("create() 호출");
		String randomString = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		String dateString = dateFormat.format(new Date());
		// 이미지 없을때 기본 이미지 설정해둔거임
		String imagePath = "product/null-img.png";

		if (vo.getProductImgPath() == null || vo.getProductImgPath().isBlank()) {
			vo.setProductImgPath(imagePath);
		} else {
			String extension = "";
			extension = vo.getProductImgPath().substring(vo.getProductImgPath().lastIndexOf("."));
			imagePath = "product/" + randomString + "_" + dateString + extension;
			vo.setProductImgPath(imagePath);
		}
		int result = dao.insert(vo);
		if (result == 1 && !imagePath.equals("product/null-img.png")) {
			imageService.uploadFile(file, imagePath);

		}
		return result;
	}

	@Override
	public List<ProductVO> read(PageCriteria criteria) {
		logger.info("read(criteria) 호출");
		return dao.select(criteria);
	}

	@Override
	public List<ProductVO> readSoccerBall() {
		logger.info("read(criteria) 호출");
		return dao.selectSoccerBall();
	}

	@Override
	public List<ProductVO> readBaseBall() {
		logger.info("read(criteria) 호출");
		return dao.selectBaseBall();
	}

	@Override
	public List<ProductVO> readBasketBall() {
		logger.info("read(criteria) 호출");
		return dao.selectBasketBall();
	}

	@Override
	public ProductVO read(int productId) {
		logger.info("read(productId) 호출 : productId = " + productId);
		return dao.selectById(productId);
	}

	@Override
	public Map<String, Object> readProductById(int productId) {
		logger.info("read(productId) 호출 : productId = " + productId);
		Map<String, Object> map = new HashMap<String, Object>();
		ProductVO product = dao.selectById(productId);
		List<ReviewVO> review = reviewDAO.selectProductReview(productId);
		map.put("product", product);
		map.put("review", review);
		return map;
	}

	@Transactional
	@Override
	public int update(ProductVO vo, MultipartFile file) throws IOException {
		logger.info("update() 호출 : vo = " + vo.toString());
		String randomString = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		String dateString = dateFormat.format(new Date());
		// 이미지 없을때 기본 이미지 설정해둔거임
		String imagePath = "product/null-img.png";

		if (vo.getProductImgPath() == null || vo.getProductImgPath().isBlank()) {
			vo.setProductImgPath(imagePath);
		} else {
			String extension = "";
			extension = vo.getProductImgPath().substring(vo.getProductImgPath().lastIndexOf("."));
			imagePath = "product/" + randomString + "_" + dateString + extension;
			vo.setProductImgPath(imagePath);
		}
		int result = dao.update(vo);
		if (result == 1 && !imagePath.equals("product/null-img.png")) {
			imageService.uploadFile(file, imagePath);

		}
		return result;
	}

	@Override
	public int delete(int productId) {
		logger.info("delete()  호출: productName = " + productId);
		return dao.delete(productId);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() 호출");
		return dao.getTotalCounts();
	}

	@Override
	public List<ProductVO> readBySearchText(String searchText, PageCriteria criteria) {
		logger.info("serach() 호출");
		return dao.select(searchText, criteria);
	}

	@Override
	public List<ProductVO> searchLikesCount(String searchText, PageCriteria criteria) {
		logger.info("searchLikesCount() 호출");
		return dao.selectLikesCount(searchText, criteria);
	}

	@Override
	public List<ProductVO> searchRating(String searchText, PageCriteria criteria) {
		logger.info("searchRating() 호출");
		return dao.selectRating(searchText, criteria);
	}

	@Override
	public List<ProductVO> searchReviewCount(String searchText, PageCriteria criteria) {
		logger.info("searchReviewCount() 호출");
		return dao.selectReviewCount(searchText, criteria);
	}

	@Override
	public int softDelete(ProductVO vo) {
		logger.info("softDelete호출");
		return dao.softDelete(vo);
	}

}