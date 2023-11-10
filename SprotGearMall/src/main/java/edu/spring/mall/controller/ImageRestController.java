package edu.spring.mall.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.spring.mall.service.ImageService;

@RestController
@RequestMapping
public class ImageRestController {
	private final Logger logger = LoggerFactory.getLogger(ImageRestController.class);
	
	
	
	@Autowired
	private ImageService service;
	
	@PostMapping(value="/notice/uploadImg", produces = "application/json")
	public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("upload") MultipartFile file) {
	    logger.info("uploadFile");
	    try {
	        String randomString = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
	        String dateString = dateFormat.format(new Date());
	        
	        String fileName = "notice/" + randomString + dateString + ".jpg"; 
	        String fileUrl = service.noticeImg(file, fileName); 
	        Map<String, Object> response  = new HashMap<String, Object>();
	        response.put("url", fileUrl);
	       
	      
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        Map<String, Object> response  = new HashMap<String, Object>();
	        response.put("error", "업로드 실패");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

	    	
	    }
		
	}
}
