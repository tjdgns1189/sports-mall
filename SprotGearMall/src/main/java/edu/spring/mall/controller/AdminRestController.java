package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.service.MemberService;

@RestController
public class AdminRestController {
	private final Logger logger = LoggerFactory.getLogger(AdminRestController.class);
	
	@Autowired
	private MemberService service;
	
	
	@DeleteMapping("/admin/deleteUser")
	public ResponseEntity<String> userDelete(@RequestBody MemberVO vo) throws Exception{
		logger.info("userDelete 호출");
		int result = 0;
		String response="false";
		logger.info("memberId : " + vo.getMemberId());
		result = service.delete(vo.getMemberId());
		if(result == 1) {
			logger.info("아이디 삭제 성공");
			response="success";
			return new ResponseEntity<String>(response, HttpStatus.OK);	
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
