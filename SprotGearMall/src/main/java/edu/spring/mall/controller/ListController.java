package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.mall.controller.LoginController;
import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.persistence.ProductDAO;

@Controller
@RequestMapping(value = "/product")
public class ListController {
	private static final Logger logger =
			LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ProductDAO dao;
	
	@GetMapping("/list")
	public void listGET(Model model) {
		logger.info("listGET() 호출");
		
		List<ProductVO> list = dao.select();
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/detail")
	public void detailGET(Model model, Integer productId) {
		logger.info("detailGET() 호출");
		ProductVO vo = dao.select(productId);
		model.addAttribute("vo", vo);
	}
	
	@GetMapping("/payment")
	public void paymentGET(Model model, Integer productId) {
		logger.info("paymentGET() 호출");
		ProductVO vo = dao.select(productId);
		model.addAttribute("vo", vo);
	}
	

	
	
	
}
