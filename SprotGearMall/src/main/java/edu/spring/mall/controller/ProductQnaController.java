package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.service.ProductQnaService;

@Controller
@RequestMapping
public class ProductQnaController {
	private final Logger logger = LoggerFactory.getLogger(ProductQnaController.class);
	
	@Autowired
	private ProductQnaService service;
	
	
	@GetMapping("/product/prdQna")
	public void prdQnaGET(Model model, int productId) {
		logger.info("prdQnaGET 호출");
		model.addAttribute("productId", productId);
	}
	
	
	@GetMapping("/member/myqna")
	public void myqnaGet(Model model) {
		logger.info("myqna 호출");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String memberId = auth.getName();
		List<ProductQnaVO> list = service.read(memberId);
		model.addAttribute("list", list);
	}
}
