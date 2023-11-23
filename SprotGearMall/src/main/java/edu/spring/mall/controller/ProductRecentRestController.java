package edu.spring.mall.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductRecentRestController {
	private static final Logger logger =
			LoggerFactory.getLogger(ProductRecentRestController.class);
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/recents")
	public ResponseEntity<List<ProductVO>> openRecent(@RequestParam String recentProductsCookieValue){
		logger.info("openRecent()에서 recentProductsCookieValue 호출  = " + recentProductsCookieValue);
		String[] stringValues = recentProductsCookieValue.split(",");
		
		// 문자열 배열을 정수 배열로 변환
	    int[] recentProductsList = Arrays.stream(stringValues)
	                                       .mapToInt(Integer::parseInt)
	                                       .toArray();
	    logger.info("recentProductsList리스트로 바꿈" + recentProductsList);
		
		int result = 0;
		List<ProductVO> productList = new ArrayList<>();
		
		for (int productId : recentProductsList) {
			ProductVO product = service.read(productId);
	        productList.add(product);
		}
		
		logger.info("productList보내기 직전임" + productList);
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
}
