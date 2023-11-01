package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.pageutil.PageMaker;
import edu.spring.mall.service.ProductService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/adminPage")
	public void adminPageGET() {
		logger.info("adminPageGET 호출");
	}
	
	@GetMapping("/memberList")
	public void memberListGET() {

	}
	
	@GetMapping("/productList")
	public void productListGET(Model model, Integer page, Integer numsPerPage) {
		logger.info("productList 호출");

		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}

		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<ProductVO> list = productService.read(criteria);
		model.addAttribute("list", list);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(productService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

		logger.info("memberList 호출");
	}
	
	@GetMapping("/orderManage")
	public void orderManagementGET() {
		logger.info("orderManage 호출");
	}
}
