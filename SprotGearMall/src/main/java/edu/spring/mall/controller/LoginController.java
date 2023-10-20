package edu.spring.mall.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.persistence.MemberDAO;

@RestController
@RequestMapping
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private MemberDAO dao;


	@PostMapping("member/checkid")
    public ResponseEntity<Integer> checkId(@RequestBody Map<String, String> request) {
		logger.info("아이디 중복체크 메소드 호출");
		Integer result = dao.select(request.get("memberId"));
		logger.info("중복 아이디 갯수 : " + result);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
		
	}
	@PostMapping("/register")
	public ResponseEntity<Integer> registerPOST(
			   @RequestParam("username") String memberId,
	            @RequestParam("password") String password,
	            @RequestParam("name") String name,
	            @RequestParam("phone") String phone,
	            @RequestParam("email") String email,
	            @RequestParam("postcode") String postcode,
	            @RequestParam("Address") String address,
	            @RequestParam("detailAddress") String detailAddress,
	            @RequestParam("userGrade") String userGrade) {
		MemberVO vo = new MemberVO();
		int result = 1;
		return new ResponseEntity<Integer>(result, HttpStatus.OK);

		
	}
	
	

}
