package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.NotificationVO;

public interface NotificationDAO {
	
	int insert(NotificationVO vo);
	List<NotificationVO> select(String memberId);
	List<NotificationVO> selectAdmin(String group);
	int update(NotificationVO vo);
	int delete(NotificationVO vo);

}
