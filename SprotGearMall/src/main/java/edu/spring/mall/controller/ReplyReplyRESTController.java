package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.ReplyReplyVO;
import edu.spring.mall.service.ReplyReplyService;

@RestController
@RequestMapping(value="/qnaBoard/replyReplies")
public class ReplyReplyRESTController {
	private static final Logger logger = 
			LoggerFactory.getLogger(ReplyReplyRESTController.class);
	
	@Autowired
	private ReplyReplyService replyReplyService;
	
	@PostMapping
	public ResponseEntity<Integer> createReply(@RequestBody ReplyReplyVO vo) {
		logger.info("createReply() 호출 : vo = " + vo.toString());

		int result = 0;
		try {
			result = replyReplyService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all/{qnaReplyId}")
	public ResponseEntity<List<ReplyReplyVO>> readReplies(
			@PathVariable("qnaReplyId") int qnaReplyId) {
		logger.info("readReplies() 호출 : qnaReplyId = " + qnaReplyId);
		
		List<ReplyReplyVO> list = replyReplyService.read(qnaReplyId);
		return new ResponseEntity<List<ReplyReplyVO>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/{replyReplyId}")
	public ResponseEntity<Integer> updateReply(
			@PathVariable("replyReplyId") int replyReplyId,
			@RequestBody String replyReplyContent
			){
		int result = replyReplyService.update(replyReplyId, replyReplyContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{replyReplyId}")
	public ResponseEntity<Integer> deleteReply(
			@PathVariable("replyReplyId") int replyReplyId){
		logger.info("replyReplyId = " + replyReplyId);
		
		int result = 0;
		try {
			result = replyReplyService.delete(replyReplyId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}