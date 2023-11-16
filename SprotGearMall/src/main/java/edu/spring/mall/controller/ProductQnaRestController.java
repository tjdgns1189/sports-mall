package edu.spring.mall.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.service.ProductQnaService;

@RestController
@RequestMapping
public class ProductQnaRestController {
	private final Logger logger = LoggerFactory.getLogger(ProductQnaRestController.class);
	
	@Autowired
	private ProductQnaService service;
	
	@PostMapping(value = "/product/prdQna", produces = "application/json")
	public ResponseEntity<String> prdQnaPOST(@ModelAttribute ProductQnaVO vo){
		logger.info("prdQnaPost호출");
		String result = "";
	    String success = "{\"result\":\"success\"}";

		if(vo == null) {
			logger.info("vo가 null임");
		    return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
		}

			int dbinsert = service.create(vo);
			if(dbinsert == 1) {
				result = success;
			}

	    return new ResponseEntity<String>(result,HttpStatus.OK);

	}

}
