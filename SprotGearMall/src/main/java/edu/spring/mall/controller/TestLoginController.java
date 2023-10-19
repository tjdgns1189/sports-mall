package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.persistence.MemberDAO;
import edu.spring.mall.service.MemberService;

@Controller	
@RequestMapping(value = "member")

public class TestLoginController {
	private final Logger logger = LoggerFactory.getLogger(TestLoginController.class);
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userService;

	
	@GetMapping("/loginForm")
	public void loginGet(Model model, 
			@RequestParam(name = "error", required = false) String error) {
		logger.info("loginGet 호출");
		if(error != null) {
			model.addAttribute("error", "error");
		}
	}
	
	@PostMapping("/login")
	public String loginPost(String memberId, String password) {
		logger.info("loginPOST 호출");
	    
	    try {
		    UserDetails user = userService.loadUserByUsername(memberId);

		    String encodedPassword= user.getPassword();
		    if(passwordEncoder.matches(password, encodedPassword)) {
		    	logger.info("로그인 성공");
		    	return "redirect:/index";
		    }
		    logger.info("로그인 실패");
		    return "redirect:/member/loginForm?error";
		} catch (UsernameNotFoundException e) {
			logger.info("아이디 조회 실패");
			e.printStackTrace();
		    return "redirect:/member/loginForm?error";

		}
	}
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("loginGet 호출");
	}
	
	@PostMapping("/register")
	public String registerPOST(@RequestParam("memberId") String memberId,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("postcode") String postcode,
            @RequestParam("address") String address,
            @RequestParam("detailAddress") String detailAddress,
            @RequestParam("userGrade") String userGrade) {
		
		String addressStr = postcode + " " + address + " " + detailAddress;
		MemberVO vo = new MemberVO(memberId, password, name, phone, email, addressStr, userGrade);
		try {
			int result = service.create(vo);
			if(result == 1) {
				logger.info("회원가입 성공");
				return "redirect:/member/loginForm";
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/member/register";
	}
	
	@GetMapping("/info")
	public void infoGET(Model model, String memberId) {
		
		
	}
	

	
	
}
