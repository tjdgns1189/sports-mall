package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.EventBoardVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface EventBoardService {
	int create(EventBoardVO vo);
	List<EventBoardVO> read(PageCriteria criteria);
	int update(EventBoardVO vo);
	int delete(int eventBoardId);
	EventBoardVO read(int eventBoardId);
	int getTotalCounts();
}
