package edu.spring.mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.NotificationVO;
import edu.spring.mall.persistence.NotificationDAO;

@Service
public class NotificationServiceImple implements NotificationService {
	private final Logger logger = LoggerFactory.getLogger(NotificationServiceImple.class);
	
	@Autowired
	private NotificationDAO dao;

	@Override
	public int create(NotificationVO vo) {
		logger.info("create 호출");
		return dao.insert(vo);
	}

	@Override
	public List<NotificationVO> read(String memberId) {
		return dao.select(memberId);
	}

	@Override
	public List<NotificationVO> readGroup(String group) {
		return dao.selectAdmin(group);
	}

	@Override
	public int update(NotificationVO vo) {
		logger.info("update");
		return dao.update(vo);
	}

	@Override
	public int delete(NotificationVO vo) {
		logger.info("delete");
		return dao.delete(vo);
	}

	@Override
	public int count(NotificationVO vo) {
		logger.info("count호출");
		return dao.count(vo);
	}

}
