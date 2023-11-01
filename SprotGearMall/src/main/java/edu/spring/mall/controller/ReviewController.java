package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.service.ProductService;
import edu.spring.mall.service.ReviewService;

@Controller
public class ReviewController {
	private final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/product/review")
	public void reviewGET(Model model, int productId) {
		logger.info("reviewGET »£√‚");
		ProductVO vo = productService.read(productId);
		model.addAttribute("vo", vo);
	}
	
	@PostMapping("/product/review")
	public void reviewPost(){
		
	}

}
