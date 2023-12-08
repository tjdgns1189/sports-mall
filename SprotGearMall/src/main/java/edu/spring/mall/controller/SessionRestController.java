package edu.spring.mall.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionRestController {
	private final Logger logger = LoggerFactory.getLogger(SessionRestController.class);

	
	@GetMapping("/loginSetSession")
	public ResponseEntity<String> loginSessionGet(HttpServletRequest request){
		logger.info("loginSessionGet");
		request.getSession().setAttribute("authError", "HEADER");
		String response = "success";
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
