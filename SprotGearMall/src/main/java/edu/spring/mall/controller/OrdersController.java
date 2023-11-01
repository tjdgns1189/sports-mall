
package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/orderlist")
	public void ordersPOST(Model model, OrdersVO vo) {
		logger.info("paymentPOST() 호출 : vo = " + vo.toString());
		int result = dao.insert(vo);
		String memberId = vo.getMemberId();
		
		List<OrdersVO> list = dao.select(memberId);
		model.addAttribute("list", list);
	}
	
	@GetMapping("/orderlist")
	public void orderlistGET(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        String memberId = userDetails.getUsername(); // memberId를 가져옵니다.
	        // 이제 memberId를 사용하여 원하는 작업을 수행할 수 있습니다.

		
		logger.info("paymentGET() 호출 : memberId = " + memberId);
		List<OrdersVO> list = dao.select(memberId);
		model.addAttribute("memberId", memberId);
		model.addAttribute("list", list);
		logger.info("list" + list.toString());
	}
	
//	@PostMapping("/orderlist")
//	public void ordersPOST(Model model, String memberId) {
//		logger.info("paymentPOST() 호출 : memberId = " + memberId);
//
//		List<OrdersVO> list = dao.select(memberId);
//		model.addAttribute("list", list);
//
//	}
	
	@PostMapping("/delete")
	public ResponseEntity<Integer> ordersDeletePOST(@RequestBody List<Integer> checkedIds) {
		logger.info("orderDeletePOST() 호출 : " + checkedIds.toString());
		int totalDeleted = 0; // 삭제된 항목 수를 추적하는 변수

	    try {
	        for (Integer id : checkedIds) {
	            int result = dao.delete(id);
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

