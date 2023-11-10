package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.CartVO;
import edu.spring.mall.persistence.CartDAO;

@RestController
@RequestMapping(value="/cart")
public class CartRestController {
	private static final Logger logger = 
			LoggerFactory.getLogger(CartRestController.class);
	
	@Autowired
	private CartDAO cartdao;
	
	@PostMapping(value="/cartlists", produces = "application/json")
	public ResponseEntity<Integer> createCart(@RequestBody CartVO vo) {
		logger.info("createCart() 호출 : vo = " + vo.toString());
		
		int result = 0;
		try {
			result = cartdao.insert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	
}
