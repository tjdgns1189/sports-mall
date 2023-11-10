package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.NoticeVO;

public interface NoticeService {
	int create(NoticeVO vo);
	List<NoticeVO> read();
	List<NoticeVO> read(String noticeTitle);
	NoticeVO read(int noticeId);
	int update(NoticeVO vo);
	int delete(int noticeId);

}
