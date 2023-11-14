package edu.spring.mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.NoticeVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.persistence.NoticeDAO;
@Service
public class NoticeServiceImple implements NoticeService {
	private final Logger logger = LoggerFactory.getLogger(NoticeServiceImple.class);
	
	@Autowired
	private NoticeDAO dao;
	
	
	@Override
	public int create(NoticeVO vo) {
		logger.info("create 호출");
		return  dao.insert(vo);
	}

	@Override
	public List<NoticeVO> read(PageCriteria criteria) {
		logger.info("read() 호출");
		return dao.select(criteria);
	}

	@Override
	public List<NoticeVO> read(String noticeTitle) {
		logger.info("read(noticeTitle)");
		return dao.select(noticeTitle);
	}

	@Override
	public int update(NoticeVO vo) {
		logger.info("update");
		return dao.update(vo);
	}

	@Override
	public int delete(int noticeId) {
		logger.info("delete");
		return dao.delete(noticeId);
	}

	@Override
	public NoticeVO read(int noticeId) {
		logger.info("read(noticeId)");
		return dao.select(noticeId);
		
	}


	@Override
	public int getTotalCount() {
		logger.info("getTotalCount호출");
		return dao.count();
	}

}
