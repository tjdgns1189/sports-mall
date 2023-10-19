package edu.spring.mall.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.mall.persistence.MemberDAO;

//@Controller
//@RequestMapping(value = "/login")

public class TestLoginController {
	
//	@Autowired
//	private MemberDAO dao;
//
//	
//	@GetMapping("/login")
//	public void loginGet() {
//	}
//	
//	@PostMapping("/login")
//	public String loginPost(String memberId, String password, HttpServletRequest request) {
//		MemberVO vo = dao.select(memberId, password);
//		if(vo != null) {
//			HttpSession session = request.getSession();
//			session.setAttribute("memberId", memberId);			
//			return "redirect:/";
//		}else {
//			return "redirect:/login";
//		}
//		return "redirect:/login";
//	}
//	
//	@GetMapping("/register")
//	public void registerGET() {
//		
//	}
//	
	
}
