package edu.spring.mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.ProductVO;

@Service
public class LikesServiceImple implements LikesService {
	private final Logger logger = LoggerFactory.getLogger(LikesServiceImple.class);
	
	
	
	
	
	@Override
	public List<ProductVO> read(String memberId) {
		logger.info("read »£√‚");
		return null;
	}

}
