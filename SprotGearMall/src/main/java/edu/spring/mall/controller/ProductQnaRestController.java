package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping(value = "/product/prdQnaUpdate", produces = "application/json")
	public ResponseEntity<String> prdQnaPut(@ModelAttribute ProductQnaVO vo){
		logger.info("prdQnaPost호출");
		String result = "";
	    String success = "{\"result\":\"success\"}";

		if(vo == null) {
			logger.info("vo가 null임");
		    return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
		}
			int dbinsert = service.update(vo);
			if(dbinsert == 1) {
				result = success;
			}
	    return new ResponseEntity<String>(result,HttpStatus.OK);

	}
	
	@GetMapping("/product/detail/qnapage")
	public ResponseEntity<List<ProductQnaVO>> detailQnaPaging(int productId,int page){
		//이자리에 페이징 처리된거 보내면 됨
		//서비스에서 productId랑 페이지 보내서 처리시키기
		List<ProductQnaVO> list = "";
		
		return new ResponseEntity<List<ProductQnaVO>>(HttpStatus.OK);
	}

}
