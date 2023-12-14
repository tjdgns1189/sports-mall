package edu.spring.mall.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.LikesVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.persistence.LikesDAO;
import edu.spring.mall.persistence.ProductDAO;

@Service
public class LikesServiceImple implements LikesService {
	private final Logger logger = LoggerFactory.getLogger(LikesServiceImple.class);

	@Autowired
	ProductDAO productDAO;

	@Autowired
	LikesDAO likesDAO;

	@Override
	public List<ProductVO> read(String memberId) {
		logger.info("read 호출");

		List<Integer> likeNumber = likesDAO.selectUserLiked(memberId);
		List<ProductVO> likedProducts = new ArrayList<ProductVO>();

		for (Integer x : likeNumber) {
			ProductVO vo = productDAO.selectById(x);
			likedProducts.add(vo);
		}

		return likedProducts;
	}

	@Override
	public int count(LikesVO vo) {
		logger.info("count호출");
		return likesDAO.select(vo);
	}

	@Override
	public int create(LikesVO vo) throws Exception {
		logger.info("create 호출");
		return likesDAO.insert(vo);
	}

	@Override
	public int delete(LikesVO vo) throws Exception {
		logger.info("delete 호출");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		vo.setMemberId(auth.getName());
		return likesDAO.delete(vo);
	}

}
