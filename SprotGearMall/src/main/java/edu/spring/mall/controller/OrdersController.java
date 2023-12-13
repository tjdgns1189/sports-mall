
package edu.spring.mall.controller;

import java.security.Principal;
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

import edu.spring.mall.domain.OrdersProductJoinVO;
import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.service.OrderService;

@Controller
@RequestMapping(value = "/orders")
public class OrdersController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private OrderService service;

	@PostMapping("/orderlist")
	public String ordersPOST(Model model, OrdersVO vo, Principal principal) throws Exception {
		logger.info("paymentPOST() 호출 : vo = " + vo.toString());

		int result = service.create(vo);
		String memberId = principal.getName();
		List<OrdersVO> list = service.readOrder(memberId);
		model.addAttribute("memberId", memberId);
		model.addAttribute("list", list);

		return "redirect:/orders/orderlist";
	}

	@GetMapping("/orderlist")
	public void orderlistGET(Model model, Principal principal) throws Exception {
		String memberId = principal.getName();
		logger.info("orderlistGET 호출 : memberId = " + memberId);
		List<OrdersProductJoinVO> list = service.read(memberId);
		model.addAttribute("list", list);
		model.addAttribute("memberId", memberId);
	}

	@PostMapping(value = "/delete", produces = "application/json")
	public ResponseEntity<Integer> ordersDeletePOST(@RequestBody List<Integer> checkedIds) {
		logger.info("orderDeletePOST() 호출 : " + checkedIds.toString());
		int totalDeleted = 0; // 삭제된 항목 수를 추적하는 변수
		try {
			for (Integer id : checkedIds) {
				int result = service.delete(id);
				totalDeleted += result;
			}

			if (totalDeleted > 0) {
				// 적어도 하나의 항목이 성공적으로 삭제됐을 경우
				return new ResponseEntity<>(totalDeleted, HttpStatus.OK);
			} else {
				// 삭제된 항목이 없는 경우

				return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
