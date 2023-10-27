package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.LikesVO;

@RestController
@RequestMapping
public class RestLikeController {
	private final Logger logger = LoggerFactory.getLogger(RestLikeController.class);
	

	
	
//	@PostMapping("/like")
//	public ResponseEntity<String> InsertLike(@RequestBody LikesVO vo){
//		logger.info("좋아요 insert");
//		String result = "";
//		
//		//처음 디테일 들어갔을때 좋아요인지 아닌지 판단해야함
//		//버튼을 누르면 memberId productId를 가져올거임
//
//		
//		logger.info("좋아요 기능 수행");
//		
//		

////		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}
}
