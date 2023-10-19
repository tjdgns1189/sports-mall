package edu.spring.mall.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.mall.controller.LoginController;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger logger =
			LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/login")
	public void login() {
		
	}
	

	@PostMapping("/login")
	public String loginPOST(String memberId, String password, HttpServletRequest request) {
		logger.info("loginPOST() 호출");
		if(memberId.equals("test") && password.equals("1234")) {
			logger.info("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("memberId", memberId);
			session.setMaxInactiveInterval(60000);
			return "redirect:/board/list";
		} else {
			return "redirect:/board/login";
		}
	}
}
