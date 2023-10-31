package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.ReplyReplyVO;

public interface ReplyReplyService {
	int create(ReplyReplyVO vo) throws Exception;
	List<ReplyReplyVO> read(int qnaReplyId);
	int update(int replyReplyId, String replyReplyContent);
	int delete(int replyReplyId) throws Exception;
}
