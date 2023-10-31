package edu.spring.mall.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.service.LikesServiceImple;

@Controller
public class LikesController {
	private final Logger logger = LoggerFactory.getLogger(LikesController.class);
	
	@Autowired
	LikesServiceImple service;
	
	@GetMapping("/member/likes")
	public void likesGET(Principal principal, Model model) {
		logger.info("likesGET »£√‚" + principal.getName());
		String memberId = principal.getName();
		
		List<ProductVO> list = service.read(memberId);
		model.addAttribute("list", list);
		    
	}

}
