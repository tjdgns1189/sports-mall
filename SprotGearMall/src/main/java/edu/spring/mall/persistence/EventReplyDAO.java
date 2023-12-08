package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.EventReplyVO;

public interface EventReplyDAO {
	int insert(EventReplyVO vo);
	int update(EventReplyVO vo);
	int delete(int eventReplyId);
	List<EventReplyVO> select(int eventBoardId);
}
