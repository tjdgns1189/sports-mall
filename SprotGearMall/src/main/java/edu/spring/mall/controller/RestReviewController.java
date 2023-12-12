package edu.spring.mall.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.service.OrderService;
import edu.spring.mall.service.ReviewService;

@RestController
@RequestMapping
public class RestReviewController {
	private final Logger logger = LoggerFactory.getLogger(RestReviewController.class);
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private OrderService orderService;
	
	
	
	@PostMapping("/member/review")
	public ResponseEntity<String> reviewPost(@RequestBody ReviewVO vo, Principal principal) throws Exception {
	    logger.info("reviewPOST 호출");
	    if (principal == null) {
	        logger.info("로그인 정보 찾을 수 없음");
	        return new ResponseEntity<>("로그인 필요", HttpStatus.FORBIDDEN);
	    }

	    String memberId = principal.getName();
	    vo.setMemberId(memberId);

	    boolean orderExists = false;
	    List<OrdersVO> orders = orderService.readOrder(memberId);
	    for (OrdersVO order : orders) {
	        if (order.getOrderId() == vo.getOrderId()) {
	            orderExists = true;
	            break;
	        }
	    }

	    if (!orderExists) {
	        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
	    }

	    int duplicateCount = reviewService.count(vo);
	    if (duplicateCount > 0) {
	        logger.info("이미 작성한 리뷰");
	        return new ResponseEntity<>("duplicate", HttpStatus.BAD_REQUEST);
	    }

	    int dbResult = reviewService.create(vo);
	    if (dbResult == 1) {
	        logger.info("리뷰 등록 성공");
	        return new ResponseEntity<>("success", HttpStatus.OK);
	    }

	    return new ResponseEntity<>("리뷰 등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
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
