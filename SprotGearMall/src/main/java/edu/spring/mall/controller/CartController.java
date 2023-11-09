package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.mall.persistence.CartDAO;
import edu.spring.mall.service.CartService;
import edu.spring.mall.service.ProductService;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
	private static final Logger logger =
			LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private CartDAO dao;
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/cartlist")
	public void cartlistGET() {
		
	}
	
	@PostMapping("/cartlist")
	public String cartPost() {
		return "redirect:/cart/cartlist";
	}
	
	
	
}
