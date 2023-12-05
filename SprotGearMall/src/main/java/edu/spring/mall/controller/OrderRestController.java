package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.service.OrderService;

@RestController
public class OrderRestController {
	private Logger logger = LoggerFactory.getLogger(OrderRestController.class);
	
	@Autowired
	private OrderService service;
	
	@PutMapping("/refund")
	public ResponseEntity<String> refundPut(@RequestBody OrdersVO vo) throws Exception{
		logger.info("refundPut 호출");
		String response = "";
		int result = service.update(vo);
		if(result == 1) {
			response = "success";
			return new ResponseEntity<String>(response,HttpStatus.OK);
		}
	return new ResponseEntity<String>(response,HttpStatus.OK);

		
	}

}
