package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.NoticeVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface NoticeDAO {
	
	int insert(NoticeVO vo);
	List<NoticeVO>select();
	List<NoticeVO>select(String boardTitle);
	List<NoticeVO> select(PageCriteria criteria);
	NoticeVO select(int noitceId);
	int count();
	int update(NoticeVO vo);
	int delete(int noitceId);
}
