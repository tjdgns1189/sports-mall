package edu.spring.mall.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	public String uploadFile(MultipartFile file, String imagePath) throws IOException;
	public String noticeImg(MultipartFile file, String imagePath) throws IOException;
}
