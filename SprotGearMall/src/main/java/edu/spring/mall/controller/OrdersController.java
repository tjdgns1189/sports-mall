
package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.persistence.OrdersDAO;

@Controller
@RequestMapping(value = "/orders")
public class OrdersController {
	private static final Logger logger =
			LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private OrdersDAO dao;
	
	@GetMapping("/orderlist")
	public void orderlistGET(Model model, String memberId) {
		logger.info("paymentGET() 호출 : memberId = " + memberId);
		List<OrdersVO> list = dao.select(memberId);
		model.addAttribute("memberId", memberId);
		model.addAttribute("list", list);
		logger.info("list" + list.toString());
	}
	
	@PostMapping("/orderlist")
	public void ordersPOST(Model model, String memberId) {
		logger.info("paymentPOST() 호출 : memberId = " + memberId);

		List<OrdersVO> list = dao.select(memberId);
		model.addAttribute("list", list);

	}
}

