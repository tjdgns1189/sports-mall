package edu.spring.mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.ProductQnaJoinReplyVO;
import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.persistence.ProductQnaDAO;
@Service
public class ProductQnaServiceImple implements ProductQnaService {
	private final Logger logger = LoggerFactory.getLogger(ProductQnaServiceImple.class);
	
	@Autowired
	private ProductQnaDAO dao;
	
	@Override
	public int create(ProductQnaVO vo) {
		logger.info("create 호출");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String memberId = authentication.getName();
		vo.setMemberId(memberId);
	    vo.setPrdQnaContent(vo.getPrdQnaContent().replace("\n", "<br>"));
		return dao.insert(vo);
	}
	//전체 문의 출력
	@Override
	public List<ProductQnaVO> read() {
		logger.info("read 호출 전체출력");
		return dao.select();
	}
	/**
	 * 디테일 접근
	 */
	@Override
	public List<ProductQnaJoinReplyVO> read(int productId, PageCriteria criteria) {
		logger.info("read(productId) 호출");
		List<ProductQnaJoinReplyVO> list = dao.select(productId,criteria);
		logger.info("qna 확인 : " + list.get(0).getQna().toString());
		logger.info("reply 확인 : " + list.get(0).getReply().toString());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String memberId = auth.getName();
		boolean isAdmin = auth.getAuthorities().stream()
		                      .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
		
		
		for(ProductQnaJoinReplyVO qna : list) {
		    qna.getQna().setAdmin(isAdmin);;
		    qna.getQna().setAuthor(memberId.equals(qna.getQna().getMemberId()));
		    if(!qna.getQna().isAuthor()) {
		        String maskedMemberId = qna.getQna().getMemberId().substring(0, 3) + "***";
		        qna.getQna().setMemberId(maskedMemberId);
		    }
		}
		return list;
	}
	
	//유저 개인 문의
	@Override
	public List<ProductQnaVO> read(String memberId) {
		logger.info("read(유저 개인문의) 호출");
		return dao.select(memberId);
	}
	
	/**
	 * 업데이트 밸류 호출
	 */
	@Override
	public ProductQnaVO readDetail(int prdQnaId) {
		logger.info("readDetail 호출");
		return dao.selectDetail(prdQnaId);
	}

	@Override
	public int update(ProductQnaVO vo) {
		logger.info("update 호출");
		return dao.update(vo);
	}

	@Override
	public int delete(int prdQnaId) {
		logger.info("delete 호출");
		return dao.delete(prdQnaId);
	}
	@Override
	public int getTotalCounts(int productId) {
		logger.info("getTotalCounts 호출");
		return dao.getTotalCount(productId);
	}


}
