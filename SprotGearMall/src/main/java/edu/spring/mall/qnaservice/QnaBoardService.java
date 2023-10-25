package edu.spring.mall.qnaservice;

import java.util.List;

import edu.spring.mall.domain.QnaBoardVO;
import edu.spring.mall.qnapageutil.PageCriteria;

public interface QnaBoardService {
	int create(QnaBoardVO vo);
	List<QnaBoardVO> read(PageCriteria criteria);	
	QnaBoardVO read(int qnaBoardId);
	int getTotalCounts();
}
