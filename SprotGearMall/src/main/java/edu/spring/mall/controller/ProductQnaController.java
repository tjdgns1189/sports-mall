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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/product/prdQnaupdate")
	public void prdQnaupdateGET(Model model, int prdQnaId) {
		logger.info("prdQnaUpdateGET 호출 : " + prdQnaId);
		ProductQnaVO vo = service.readDetail(prdQnaId);
		model.addAttribute("vo",vo);
	}
	
	
	
	@GetMapping("/member/myqna")
	public void myqnaGet(Model model) {
		logger.info("myqna 호출");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String memberId = auth.getName();
		List<ProductQnaVO> list = service.read(memberId);
		model.addAttribute("list", list);
	}
	
	
	@PostMapping("/member/deleteMyQna")
	public String myqndaDeletePOST(int prdQnaId) {
		logger.info("myqndaDeletePOST() 호출");
		int result = 0;
		result = service.delete(prdQnaId);
		if(result == 1) {
			logger.info("삭제성공");
		}else {
			logger.info("삭제 실패");
		}
		return "redirect:/member/myqna";
		
		
	}
}
