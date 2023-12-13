package edu.spring.mall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.mall.domain.NotificationVO;
import edu.spring.mall.domain.ProductQnaReplyVO;
import edu.spring.mall.persistence.ProductQnaReplyDAO;

@Service
public class ProductQnaReplyServiceImple implements ProductQnaReplyService {
	private final Logger logger = LoggerFactory.getLogger(ProductQnaReplyServiceImple.class);

	@Autowired
	private ProductQnaReplyDAO dao;

	@Autowired
	private NotificationService notificationService;

	@Transactional
	@Override
	public int create(ProductQnaReplyVO vo, String authorId) throws Exception {
		logger.info("create 호출");
		int result = 0;
		vo.setPqrContent(vo.getPqrContent().replace("\n", "<br>"));
		int success = dao.insert(vo);

		if (success == 1) {
			result = 1;

			// 넣어야하는거
			// memberId content url type entityId
			String memberId = authorId; // 답변에 대한 멤버 아이디가 들어옴
			String notificationType = "문의 답변";
			int entityId = vo.getProductId();
			String targetUrl = "/mall/product/detail?productId=" + entityId;
			// 상품 문의에 대한 답변이 도착했습니다 클릭시 이동됩니다
			String notificationContent = "상품 문의에 대한 답변이 등록되었습니다 클릭시 이동됩니다";
			NotificationVO notification = new NotificationVO(0, memberId, null, notificationType, notificationContent,
					targetUrl, null, null, entityId);
			notificationService.create(notification);
		}
		return result;
	}

	@Override
	public int update(ProductQnaReplyVO vo) {
		logger.info("update 호출");
		return dao.update(vo);
	}

	@Override
	public int delete(ProductQnaReplyVO vo) {
		logger.info("delete");
		return dao.delete(vo);
	}

}
