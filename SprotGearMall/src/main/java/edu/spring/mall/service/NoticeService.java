package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.NoticeVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface NoticeService {
	int create(NoticeVO vo);
	List<NoticeVO> read(PageCriteria criteria);
	List<NoticeVO> read(String noticeTitle);
	int getTotalCount();
	NoticeVO read(int noticeId);
	int update(NoticeVO vo);
	int delete(int noticeId);

}
