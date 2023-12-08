package edu.spring.mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.EventBoardVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.persistence.EventBoardDAO;


@Service
public class EventBaordServiceImple implements EventBoardService {

	private static final Logger logger = 
			LoggerFactory.getLogger(EventBaordServiceImple.class);
	
	@Autowired
	private EventBoardDAO dao;
	
	@Override
	public int create(EventBoardVO vo) {
		logger.info("create() 호출");
		return dao.insert(vo);
	}

	@Override
	public List<EventBoardVO> read(PageCriteria criteria) {
		logger.info("read() 호출");
		return dao.select(criteria);
	}

	@Override
	public int update(EventBoardVO vo) {
		logger.info("update() 호출");
		return dao.update(vo);
	}

	@Override
	public int delete(int eventBoardId) {
		logger.info("delete() 호출");
		return dao.delete(eventBoardId);
	}

	@Override
	public EventBoardVO read(int eventBoardId) {
		logger.info("read() 호출");
		return dao.select(eventBoardId);
	}
	
	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() 호출");
		return dao.getTotalCounts();
	}

}
