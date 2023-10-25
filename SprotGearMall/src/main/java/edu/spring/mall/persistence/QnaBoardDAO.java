package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.QnaBoardVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface QnaBoardDAO {
	int insert(QnaBoardVO vo);
	List<QnaBoardVO> select();
	QnaBoardVO select(int qnaBoardId);
	List<QnaBoardVO> select(PageCriteria criteria);
	int getTotalCounts();
}
