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

import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.persistence.OrdersDAO;
import edu.spring.mall.service.ProductService;

@RestController
@RequestMapping(value = "product")
public class ProductRecentRestController {
	private static final Logger logger = LoggerFactory.getLogger(ProductRecentRestController.class);

	@Autowired
	private ProductService service;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrdersDAO ordersDAO;

	@GetMapping("/recents")
	public ResponseEntity<List<ProductVO>> openRecent(@RequestParam String recentProductsCookieValue) {
		logger.info("openRecent()에서 recentProductsCookieValue 호출  = " + recentProductsCookieValue);
		String[] stringValues = recentProductsCookieValue.split(",");

		// 문자열 배열을 정수 배열로 변환
		int[] recentProductsList = Arrays.stream(stringValues).mapToInt(Integer::parseInt).toArray();
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

	@PostMapping(value = "/paymentAPI", produces = "application/json")
	public ResponseEntity<Integer> resultPOST(Model model, @RequestBody OrdersVO ordersVO) {
		// 여기서 ordersVO에는 JavaScript에서 보낸 데이터가 매핑됩니다.
		// 데이터를 Orders 테이블에 insert하고 그 결과에 따라 응답을 생성합니다.
		// (ordersService를 사용하여 Orders 테이블에 데이터를 insert하는 코드를 작성해야 합니다.)

		// 데이터베이스에 저장된 가격을 가져올 수 있는 메서드를 사용하여 비교
		logger.info("resultPOST() 호출 : ordersVO = " + ordersVO.toString());
		int result = ordersDAO.insert(ordersVO);

		int productId = ordersVO.getProductId();
		int orderId = ordersVO.getOrderId();
		int productQuantity = ordersVO.getProductQuantity();
		int productPrice = ordersVO.getProductPrice();
		int totalPrice = productQuantity * productPrice;
		ProductVO productVO = productService.read(productId);
		OrdersVO orderVO = ordersDAO.select(orderId);
		model.addAttribute("productVO", productVO);
		model.addAttribute("vo", ordersVO);
		model.addAttribute("totalPrice", totalPrice);
		return new ResponseEntity<>(totalPrice, HttpStatus.OK);

	}
}
