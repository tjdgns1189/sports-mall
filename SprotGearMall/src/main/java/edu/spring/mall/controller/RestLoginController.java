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
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.persistence.MemberDAO;

@RestController
@RequestMapping
public class RestLoginController {
	private static final Logger logger = LoggerFactory.getLogger(RestLoginController.class);

	@Autowired
	private MemberDAO dao;


	@PostMapping("member/checkid")
    public ResponseEntity<Integer> checkId(@RequestBody Map<String, String> request) {
		logger.info("아이디 중복체크 메소드 호출");
		Integer result = dao.select(request.get("memberId"));
		logger.info("중복 아이디 갯수 : " + result);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
		
	}
	
//	@PostMapping("/member/userUpdate")
//	public ResponseEntity<String> userUpdate(@RequestBody
//			Map<String, String> request){
//		Map<String,String> user = 
//		
//		return new ResponseEntity<String>(user, Http);
//		
//	}
	
	

}
