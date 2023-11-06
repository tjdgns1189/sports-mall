package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.QnaBoardVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface QnaBoardService {
	int create(QnaBoardVO vo);
	List<QnaBoardVO> read(PageCriteria criteria);	
	QnaBoardVO read(int qnaBoardId);
	int update(QnaBoardVO vo);
	int delete(int qnaBoardId);
	int getTotalCounts();
}
