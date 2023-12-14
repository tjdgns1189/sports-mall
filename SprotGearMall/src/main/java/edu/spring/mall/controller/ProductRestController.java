package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.service.ProductService;

@RestController
public class ProductRestController {
	private Logger logger = LoggerFactory.getLogger(ProductRestController.class);

	@Autowired
	private ProductService service;

	@PutMapping("/admin/softDelete")
	public ResponseEntity<String> softDelete(@RequestBody ProductVO vo) {
		logger.info("softDelete 호출 productId :  " + vo.getProductId() + " isDeleted : " + vo.getProductIsDeleted());
		int result = service.softDelete(vo);
		String success = "";
		if (result == 1) {
			logger.info("삭제(softDelete) 성공");
			success = "success";
			return new ResponseEntity<String>(success, HttpStatus.OK);

		}
		return new ResponseEntity<String>(success, HttpStatus.OK);
	}
}
