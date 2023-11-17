package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.CartVO;
import edu.spring.mall.persistence.CartDAO;
import edu.spring.mall.service.CartService;

@RestController
@RequestMapping(value="/cart")
public class CartRestController {
	private static final Logger logger = 
			LoggerFactory.getLogger(CartRestController.class);
	
	@Autowired
	private CartDAO cartdao;
	
	@Autowired
	private CartService cartService;
	
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
	
	@DeleteMapping(value="/delete/{id}", produces = "application/json")
	public ResponseEntity<Integer> deleteCart(@PathVariable("id") int cartId){
		logger.info("cartId = " + cartId);
		
		int result = 0;
		try {
			result = cartService.delete(cartId);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
//	@PutMapping("/update")
//	public ResponseEntity<Integer> updateCart(
//			@PathVariable("cartId") int cartId,
//			@RequestBody List<CartVO> cartList
//			){
//		int result = 0;
//      try {
//          for (CartVO cartVO : cartList) {
//              result += cartdao.update(cartVO.getCartId(), cartVO);
//          }
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}
	
	@PutMapping("/update")
	public ResponseEntity<Integer> updateCart(@RequestBody List<CartVO> cartList) {
	    int result = 0;

	    try {
	        for (CartVO cartVO : cartList) {
	            result += cartdao.update(cartVO.getCartId(), cartVO);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
