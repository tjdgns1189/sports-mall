package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.EventReplyVO;

public interface EventReplyService {
	
	
	int create(EventReplyVO vo);
	List<EventReplyVO> read(int eventBoardId);
	int update(EventReplyVO vo);
	int delete(int eventReplyId);
	
}
