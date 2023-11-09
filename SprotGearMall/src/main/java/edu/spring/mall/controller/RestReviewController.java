package edu.spring.mall.controller;

import java.security.Principal;

import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.service.ReviewService;

@RestController
@RequestMapping
public class RestReviewController {
	private final Logger logger = LoggerFactory.getLogger(RestReviewController.class);
	@Autowired
	ReviewService reviewService;
	
	@PostMapping("/member/review")
	public ResponseEntity<String> reviewPost(@RequestBody ReviewVO vo, Principal principal) throws Exception{
		logger.info("reviewPOST 호출");
		String result = "";
		String memberId = "";
		logger.info("orderId 확인용" + vo.getOrderId());
		int dbResult = 0;
		if(principal != null) {
			memberId = principal.getName();
			vo.setMemberId(memberId);
		}else {
			logger.info("로그인 정보 찾을 수 없음");
			return new ResponseEntity<String>(result, HttpStatus.FORBIDDEN);
		}
		dbResult = reviewService.create(vo);
		if(dbResult == 1) {
			logger.info("리뷰 등록 성공");
			result = "success";
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
		
	}
	
	@PutMapping("/member/reviewUpdate")
	public ResponseEntity<String> reviewUpdate(@RequestBody ReviewVO vo) 
			throws Exception{
		logger.info("review update 호출 vo : " + vo.toString());
		int result = reviewService.update(vo);
		String success="";
		if(result == 1) {
			success = "success";
		}
		return new ResponseEntity<String>(success, HttpStatus.OK);
	}
	
	@DeleteMapping("/member/reviewDelete")
	public ResponseEntity<String> reviewDelete(@RequestBody ReviewVO vo) throws Exception{
		logger.info("review delete 호출");
		int result = reviewService.delete(vo.getReviewId());
		String success= "";
		if(result ==1) {
			success = "success";
		}
		return new ResponseEntity<String>(success, HttpStatus.OK);
	}
}
