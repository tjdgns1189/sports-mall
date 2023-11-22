package edu.spring.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.ProductQnaReplyVO;
import edu.spring.mall.service.ProductQnaReplyService;
import edu.spring.mall.service.ProductQnaService;

@RestController
@RequestMapping("/product")
public class ProductQnaReplyRestController {
	private final Logger logger = LoggerFactory.getLogger(ProductQnaReplyRestController.class);
	
	@Autowired
	private ProductQnaReplyService replyService;
	
	@Autowired
	private ProductQnaService qnaService;
	
	
	@PostMapping("/qnaAnswer")
	public ResponseEntity<Void> qnaAnswerPOST(@RequestBody ProductQnaReplyVO vo){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		vo.setMemberId(auth.getName());
		logger.info("qnaAnswerPOST 호출 : " + vo.toString());
		int result = replyService.create(vo);
		if(result == 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		
	}

}
