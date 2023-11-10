package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.NoticeVO;

public interface NoticeDAO {
	
	int insert(NoticeVO vo);
	List<NoticeVO>select();
	List<NoticeVO>select(String boardTitle);
	NoticeVO select(int noitceId);
	int update(NoticeVO vo);
	int delete(int noitceId);
}
