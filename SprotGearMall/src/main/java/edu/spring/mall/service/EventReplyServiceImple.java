package edu.spring.mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.EventReplyVO;
import edu.spring.mall.persistence.EventReplyDAO;

@Service
public class EventReplyServiceImple implements EventReplyService {

	private static final Logger logger = LoggerFactory.getLogger(EventReplyServiceImple.class);

	@Autowired
	private EventReplyDAO dao;

	@Override
	public int create(EventReplyVO vo) {
		logger.info("create() 호출");
		return dao.insert(vo);
	}

	@Override
	public List<EventReplyVO> read(int eventBoardId) {
		logger.info("read() 호출");
		return dao.select(eventBoardId);
	}

	@Override
	public int update(EventReplyVO vo) {
		logger.info("update() 호출");
		return dao.update(vo);
	}

	@Override
	public int delete(int eventReplyId) {
		logger.info("delete() 호출");
		return dao.delete(eventReplyId);
	}

}
