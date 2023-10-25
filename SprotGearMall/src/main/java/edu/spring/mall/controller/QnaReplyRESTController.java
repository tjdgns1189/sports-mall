package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.QnaReplyVO;
import edu.spring.mall.domain.QnaReplyVO;
import edu.spring.mall.controller.QnaReplyRESTController;
import edu.spring.mall.qnaservice.QnaReplyService;

@RestController
@RequestMapping(value="/qnaBoard/replies")
public class QnaReplyRESTController {

	private static final Logger logger = 
			LoggerFactory.getLogger(QnaReplyRESTController.class);
	
	@Autowired
	private QnaReplyService qnaReplyService;
	
	@PostMapping
	public ResponseEntity<Integer> createReply(@RequestBody QnaReplyVO vo) {

		logger.info("createReply() 호출 : vo = " + vo.toString());
		

		int result = 0;
		try {
			result = qnaReplyService.create(vo);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all/{qnaBoardId}")
	public ResponseEntity<List<QnaReplyVO>> readReplies(
			@PathVariable("qnaBoardId") int qnaBoardId) {
		logger.info("readReplies() 호출 : qnaBoardId = " + qnaBoardId);
		
		List<QnaReplyVO> list = qnaReplyService.read(qnaBoardId);
		return new ResponseEntity<List<QnaReplyVO>>(list, HttpStatus.OK);

	}
}
