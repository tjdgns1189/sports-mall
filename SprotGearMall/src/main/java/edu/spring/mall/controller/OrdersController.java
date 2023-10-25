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
@RequestMapping(value = "/product")
public class OrdersController {
	private static final Logger logger =
			LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private OrdersDAO dao;
	
	@GetMapping("/orderlist")
	public void orderlistGET() {
		logger.info("paymentGET() 호출");
	}
	
	@PostMapping("/orderlist")
	public void ordersPOST(Model model, OrdersVO vo) {
		logger.info("paymentPOST() 호출 : vo = " + vo.toString());
		int result = dao.insert(vo);
		String memberId = vo.getMemberId();
		
		List<OrdersVO> list = dao.select(memberId);
		model.addAttribute("list", list);

	}
}
