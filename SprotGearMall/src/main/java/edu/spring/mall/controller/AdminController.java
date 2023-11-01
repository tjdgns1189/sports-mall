package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	
	@GetMapping("/adminPage")
	public void adminPageGET() {
		logger.info("adminPageGET »£√‚");
	}
}
