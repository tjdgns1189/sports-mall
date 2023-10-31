package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.QnaReplyVO;

public interface QnaReplyService {
	int create(QnaReplyVO vo) throws Exception;
	List<QnaReplyVO> read(int qnaBoardId);
	int update(int qnaReplyId, String qnaReplyContent);
	int delete(int qnaReplyId) throws Exception;
} 