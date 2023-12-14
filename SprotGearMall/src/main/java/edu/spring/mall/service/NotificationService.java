package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.NotificationVO;

public interface NotificationService {
	int create(NotificationVO vo);
	List<NotificationVO> read(String memberId);
	List<NotificationVO> readGroup(String group);
	int update(NotificationVO vo);
	int delete(NotificationVO vo);
	int count(NotificationVO vo);

}
