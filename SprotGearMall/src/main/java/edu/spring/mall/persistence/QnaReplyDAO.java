package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.QnaReplyVO;

public interface QnaReplyDAO {
	int insert(QnaReplyVO vo);
	List<QnaReplyVO> select(int qnaBoardId);
	int update(int qnaReplyId, String qnaReplyContent);
	int delete(int qnaReplyId);
}
