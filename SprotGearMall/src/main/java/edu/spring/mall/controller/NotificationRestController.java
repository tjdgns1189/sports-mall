package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.NotificationVO;
import edu.spring.mall.service.NotificationService;

@RestController
@RequestMapping
public class NotificationRestController {
	private final Logger logger = LoggerFactory.getLogger(NotificationRestController.class);
	
	
	@Autowired
	private NotificationService service;
	
    @ResponseBody
	@GetMapping("/NotificationCheck")
	public ResponseEntity<?> notificationGET(){
		logger.info("알림 확인");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String memberId = auth.getName();
		boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
		
		List<NotificationVO> notificationList;
		if(isAdmin) {
			notificationList = service.readGroup("ROLE_ADMIN");
		}else {
			notificationList = service.read(memberId);
		}
	
		return ResponseEntity.ok(notificationList);
		
	}

}
