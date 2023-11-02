
package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.ReplyReplyVO;

public interface ReplyReplyDAO {
	int insert(ReplyReplyVO vo);
	List<ReplyReplyVO> select(int qnaReplyId);
	int update(int replyReplyId, String replyReplyContent);
	int delete(int replyReplyId);
}
