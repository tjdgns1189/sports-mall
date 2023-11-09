package edu.spring.mall.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.domain.ReviewProductJoinVO;
import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.persistence.OrdersDAO;
import edu.spring.mall.service.ProductService;
import edu.spring.mall.service.ReviewService;

@Controller
public class ReviewController {
	private final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	OrdersDAO orderDAO;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/member/review")
	public void reviewGET(Model model, int productId, int orderId) {
		logger.info("reviewGET 호출" + productId + " | " + orderId);
		ProductVO productvo = productService.read(productId);
		logger.info("productvo 호출 " + productvo);
		OrdersVO ordervo = orderDAO.select(orderId);
		logger.info("order호출 : " + productvo);
		model.addAttribute("productVO", productvo);
		model.addAttribute("orderVO", ordervo);
	}
	
	
	@GetMapping("member/reviewList")
	public void reviewGET(Model model, Principal principal) throws Exception {
		logger.info("리뷰 리스트 출력");
		if(principal != null) {
			String memberId = principal.getName();
			List<ReviewProductJoinVO> list = reviewService.read(memberId);
			logger.info("리뷰한 상품 가져옴");
			model.addAttribute("list", list);
		}
	}
	
	@GetMapping("/member/reviewUpdate")
	public String reviewUpdateGET(Model model, int reviewId) throws Exception {
		logger.info("reviewUpdateGET 호출");
		ReviewVO review = reviewService.read(reviewId);
		OrdersVO order = orderDAO.select(review.getOrderId());
		logger.info("order"  +order.getProductPrice()); 
		logger.info("order"  +order.getOrderId()); 
		ProductVO product = productService.read(review.getProductId());
		model.addAttribute("reviewVO" , review);
		model.addAttribute("orderVO" ,order);
		model.addAttribute("productVO", product);
		
		return "/member/review";
	}
}
