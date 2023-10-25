package edu.spring.mall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.persistence.MemberDAO;
import edu.spring.mall.security.CustomUserDetails;
import edu.spring.mall.service.MemberService;

@Controller
@RequestMapping(value = "member")

public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private MemberDAO dao;

	@Autowired
	private MemberService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userService;

	@GetMapping("/loginForm")
	public void loginGet(Model model, @RequestParam(name = "error", required = false) String error) {
		logger.info("loginGet 호출");
		if (error != null) {
			model.addAttribute("error", "error");
		}
	}

	// 스프링 내장 로그인 기능 사용으로 주석처리
//	@PostMapping("/login")
//	public String loginPost(String memberId, String password) {
//		logger.info("loginPOST 호출");
//	    
//	    try {
//		    UserDetails user = userService.loadUserByUsername(memberId);
//
//		    String encodedPassword= user.getPassword();
//		    if(passwordEncoder.matches(password, encodedPassword)) {
//		    	logger.info("로그인 성공");
//		    	SecurityContext context = SecurityContextHolder.createEmptyContext(); 
//		    	Authentication auth =
//		    			new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//		    	SecurityContextHolder.getContext().setAuthentication(auth);
//		    	SecurityContextHolder.setContext(context); 
//
//		    	return "redirect:/index";
//		    }
//		    logger.info("로그인 실패");
//		    return "redirect:/member/loginForm?error";
//		} catch (UsernameNotFoundException e) {
//			logger.info("아이디 조회 실패");
//			e.printStackTrace();
//		    return "redirect:/member/loginForm?error";
//
//		}
//	}
//	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("loginGet 호출");
	}

	@PostMapping("/register")
	public String registerPOST(@RequestParam("memberId") String memberId, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("email") String email,
			@RequestParam("postcode") String postcode, @RequestParam("address") String address,
			@RequestParam("detailAddress") String detailAddress, @RequestParam("userGrade") String userGrade) {

		String addressStr = postcode + "." + address + "." + detailAddress;
		MemberVO vo = new MemberVO(memberId, password, name, phone, email, addressStr, userGrade);
		try {
			int result = service.create(vo);
			if (result == 1) {
				logger.info("회원가입 성공");
				return "redirect:/member/loginForm";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/member/register";
	}


	@GetMapping("/mypage")
	public void mypageGET() {

	}

	@GetMapping("/update")
	public void update(Model model) {
		logger.info("updateGet 호출");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
		String name = user.getName();
		System.out.println(name);
		String phone = user.getPhone();
		System.out.println(phone);
		String email = user.getEmail();
		System.out.println(email);
		System.out.println(user.getAddress());
		String address[] = user.getAddress().split("\\.");

		model.addAttribute("name", name);
		model.addAttribute("phone", phone);
		model.addAttribute("email", email);
		model.addAttribute("postcode", address[0]);
		model.addAttribute("address", address[1]);
		model.addAttribute("detailAddress", address[2]);

	}

	@PostMapping("/update")
	public String updatePOST(@RequestParam("memberId") String memberId, @RequestParam("name") String name,
			@RequestParam("phone") String phone, @RequestParam("email") String email,
			@RequestParam("postcode") String postcode, @RequestParam("address") String address,
			@RequestParam("detailAddress") String detailAddress) {

		logger.info("updatePOST 호출");
		String addressStr = postcode + "." + address + "." + detailAddress;
		Map<String, String> userDetail = new HashMap<String, String>();
		userDetail.put("memberId", memberId);
		userDetail.put("name", name);
		userDetail.put("phone", phone);
		userDetail.put("email", email);
		userDetail.put("address", addressStr);

		int result = 0;
		try {
			result = service.update(userDetail);
			if (result == 1) {
				logger.info("update 성공");
				return "redirect:/member/mypage";
			} else {
				logger.info("update 실패");
				return "redirect:/member/update?error";
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("예외 발생");
			return "redirect:/member/update?error";

		}

	}
	
	@PostMapping("/updatePassword")
	public String updatePasswordPOST(@RequestParam("memberId") String memberId,
			@RequestParam("newPassword") String newPassword) {
		logger.info("updatePasswordPOST 호출");
		Map<String, String> user = new HashMap<String, String>();
		user.put("memberId", memberId);
		user.put("password", newPassword);
		try {
			int result = service.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/member/mypage";
	}

	@GetMapping("/delete")
	public void deleteGET() {
	}

	@PostMapping("delete")
	public String deletePOST(@RequestParam("memberId") String memberId,
			@RequestParam("password") String password,
			HttpServletRequest request,
			HttpServletResponse response) {
		
			//비밀번호 검증을 위한 변수
		  	UserDetails user = userService.loadUserByUsername(memberId);
		    String encodedPassword = user.getPassword();
		  
		    // 비밀번호 검증
		    if (!passwordEncoder.matches(password, encodedPassword)) {
		        logger.info("비밀번호 틀림");
		        return "redirect:/member/delete?error";
		    }
		    try {
		        if (service.delete(memberId) != 1) {
		            return "redirect:/member/delete?error"; // 탈퇴 처리 실패
		        }
		        // 로그아웃 처리
		        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        if (auth != null) {
		            new SecurityContextLogoutHandler().logout(request, response, auth);
		        }
		        return "redirect:/index"; // 탈퇴 및 로그아웃 성공
		    } catch (Exception e) {
		        e.printStackTrace();
		        return "redirect:/member/delete"; 
		    }
		}
	
	
	
	
}

