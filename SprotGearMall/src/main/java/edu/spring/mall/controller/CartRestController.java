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
@RequestMapping(value = "/cart")
public class CartRestController {
	private static final Logger logger = LoggerFactory.getLogger(CartRestController.class);

	@Autowired
	private CartService cartService;

	// 장바구니 중복처리, 장바구니 추가하기
	@PostMapping(value = "/cartlists", produces = "application/json")
	public ResponseEntity<Integer> createCart(@RequestBody CartVO vo) {
		logger.info("createCart() 호출 : vo = " + vo.toString());
		logger.info("vo.getProductId()있나 = " + vo.getProductId());
		CartVO cart = cartService.readProductId(vo.getProductId(), vo.getMemberId());

		logger.info("cart있나 = " + cart);
		if (cart != null) {
			int result = 0;
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			int result = 0;
			try {
				result = cartService.create(vo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}

	}

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public ResponseEntity<Integer> deleteCart(@PathVariable("id") int cartId) {
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

	@PostMapping(value = "/update", produces = "application/json")
	public ResponseEntity<Integer> updateCart(@RequestBody List<CartVO> cartList) {
		logger.info("cartList = " + cartList.toString());
		int result = 0;

		try {
			for (CartVO cartVO : cartList) {
				result += cartService.update(cartVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
