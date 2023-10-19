package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.QnaBoardVO;

public interface QnaBoardDAO {
	int insert(QnaBoardVO vo);
	List<QnaBoardVO> select();
}
