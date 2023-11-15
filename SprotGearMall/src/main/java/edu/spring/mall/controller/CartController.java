package edu.spring.mall.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.spring.mall.domain.CartFormVO;
import edu.spring.mall.domain.CartProductJoinVO;
import edu.spring.mall.domain.CartVO;
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
	public void cartlistGET(Model model, Principal principal) throws Exception {
		String memberId = principal.getName();
		logger.info("cartlistGET 호출 : memberId = " + memberId);
		List<CartProductJoinVO> list = cartService.read(memberId);
		model.addAttribute("list", list);
	}
	
//	@GetMapping("/update")
//	public String cartPayment(Model model, Principal principal) throws Exception {
//		logger.info("cartPayment호출");
//		String memberId = principal.getName();
//		List<CartProductJoinVO> list = cartService.read(memberId);
//		model.addAttribute("list", list);
//		return "redirect:/product/payment";
//	}
	
	@PostMapping("/payment")
	public String cartPost(Model model, CartVO cartVO) throws Exception {
	    logger.info("전송된 데이터@@@@@@@@@@@@: " + cartVO.toString());
	    // 필요한 로직을 수행하세요.
	    String memberId = cartVO.getMemberId();
		List<CartProductJoinVO> list = cartService.read(memberId);
		model.addAttribute("list", list);
	    return "redirect:/product/payment";
	}

	
}
