package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.EventBoardVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface EventBoardDAO {
	int insert(EventBoardVO vo); 
	List<EventBoardVO> select(PageCriteria criteria);
	int getTotalCounts();
	EventBoardVO select(int eventBoardId);
	int update(EventBoardVO vo);
	int delete(int eventBoardId);
}
