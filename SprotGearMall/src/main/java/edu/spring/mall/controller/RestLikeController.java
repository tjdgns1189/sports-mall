package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.LikesVO;
import edu.spring.mall.persistence.LikesDAO;
import edu.spring.mall.service.LikesService;

@RestController
@RequestMapping
public class RestLikeController {
	private final Logger logger = LoggerFactory.getLogger(RestLikeController.class);

	@Autowired
	private LikesService service;

	@PostMapping("product/likes")
	public ResponseEntity<String> insertLike(@RequestBody LikesVO vo, Authentication authentication) throws Exception {
		logger.info("좋아요 insert : " + vo.toString());
		String memberId = authentication.getName();
		vo.setMemberId(memberId);
		String result = "";
		if (vo.getMemberId().isBlank() || vo.getMemberId() == null) {
			return new ResponseEntity<String>(result, HttpStatus.FORBIDDEN);
		}
		int count = service.count(vo);
		if (count == 1) {
			logger.info("중복 체크");
			result = "duplicate";
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}

		int success = service.create(vo);
		if (success == 1) {
			result = "success";
		}

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@DeleteMapping("product/likes")
	public ResponseEntity<String> deleteLike(@RequestBody LikesVO vo) throws Exception {
		logger.info("좋아요 삭제");
		String result = "";
		int success = service.delete(vo);
		if (success == 1) {
			result = "success";
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@GetMapping("/product/test/asdf")
	public ResponseEntity<Void> test() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
