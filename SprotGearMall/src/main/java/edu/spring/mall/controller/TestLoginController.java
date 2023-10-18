package edu.spring.mall.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.mall.persistence.MemberDAO;

@Controller	
@RequestMapping(value = "member")

public class TestLoginController {
	private final Logger logger = LoggerFactory.getLogger(TestLoginController.class);
	@Autowired
	private MemberDAO dao;

	
	@GetMapping("/login")
	public void loginGet() {
		logger.info("loginGet 호출");
	}
	
	@PostMapping("/login")
	public String loginPost(String memberId, String password, HttpServletRequest request) {
//		MemberVO vo = dao.select(memberId, password);
//		if(vo != null) {
//			HttpSession session = request.getSession();
//			session.setAttribute("memberId", memberId);			
//			return "redirect:/";
//		}else {
//			return "redirect:/login";
//		}
		return "redirect:/login";
	}
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("register 호출");
	}
	
	
}
