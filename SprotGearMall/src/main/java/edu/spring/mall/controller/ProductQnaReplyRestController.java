package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.ProductQnaReplyVO;
import edu.spring.mall.domain.ProductQnaVO;
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
	
	
	@PostMapping("/qnaAnswer/{authorId}")
	public ResponseEntity<Void> qnaAnswerPOST(@RequestBody ProductQnaReplyVO vo, @PathVariable String authorId) throws Exception{
		logger.info("qnaAnswerPOST 호출 authorId : " + authorId);
		 ProductQnaVO qna = qnaService.readDetail(vo.getPrdQnaId());
		 if(qna.getPrdQnaState().equals("Y")) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		 }
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		vo.setMemberId(auth.getName());
		int result = replyService.create(vo,authorId);
		if(result == 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
	}
	
	
	@PutMapping("/updateAnswer")
	public ResponseEntity<Void> qnsAnswerUpdate(@RequestBody ProductQnaReplyVO vo){
		logger.info("qnaAnswerUpdate 호출");
		int result = replyService.update(vo);
		if(result ==1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
	}
	
	@DeleteMapping("/deleteReply")
	public ResponseEntity<Void> qnaAnswerDelete(@RequestBody ProductQnaReplyVO vo){
		logger.info("Answer delete호출");
		int result = replyService.delete(vo);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
