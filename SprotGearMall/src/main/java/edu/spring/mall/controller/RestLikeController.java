package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.LikesVO;
import edu.spring.mall.persistence.LikesDAO;

@RestController
@RequestMapping
public class RestLikeController {
	private final Logger logger = LoggerFactory.getLogger(RestLikeController.class);

	@Autowired
	private LikesDAO dao;

	@PostMapping("product/likes")
	public ResponseEntity<String> insertLike(@RequestBody LikesVO vo) {
		logger.info("좋아요 insert");
		String result = "";
		int success = dao.insert(vo);
		if (success == 1) {
			result = "success";
		}

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@DeleteMapping("product/likes")
	public ResponseEntity<String> deleteLike(@RequestBody LikesVO vo) {
		logger.info("좋아요 삭제 호출");
		String result = "";
		int success = dao.delete(vo);
		if (success == 1) {
			result = "success";
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}
}
