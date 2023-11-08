package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
//	@PostMapping("upload")
//	  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
////            업로드기능 잠시 비활성화
////            String response = service.uploadFile(file, bucketName);
////            return ResponseEntity.status(HttpStatus.OK).body(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed: " + e.getMessage());
//        }
//    }
}
