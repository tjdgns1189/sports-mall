<<<<<<< HEAD
package edu.spring.mall.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.ReplyReplyVO;

@Repository
public class ReplyReplyDAOImple implements ReplyReplyDAO {
	private static final Logger logger = 
			LoggerFactory.getLogger(ReplyReplyDAOImple.class);
	
	private static final String NAMESPACE =
			"edu.spring.mall.ReplyReplyMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(ReplyReplyVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ReplyReplyVO> select(int qnaReplyId) {
		logger.info("select() 호출 : qnaReplyId = " + qnaReplyId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_qna_reply_id", qnaReplyId);
	}

	@Override
	public int update(int replyReplyId, String replyReplyContent) {
		logger.info("update() 호출");
		logger.info("replyReplyId = " + replyReplyId + ", replyReplyContent = " + replyReplyContent);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("replyReplyId", replyReplyId);
		args.put("replyReplyContent", replyReplyContent);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int replyReplyId) {
		logger.info("delete() 호출 : replyReplyId = " + replyReplyId);
		return sqlSession.delete(NAMESPACE + ".delete", replyReplyId);
	}

}
=======
package edu.spring.mall.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.ReplyReplyVO;

@Repository
public class ReplyReplyDAOImple implements ReplyReplyDAO {
	private static final Logger logger = 
			LoggerFactory.getLogger(ReplyReplyDAOImple.class);
	
	private static final String NAMESPACE =
			"edu.spring.mall.ReplyReplyMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(ReplyReplyVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ReplyReplyVO> select(int qnaReplyId) {
		logger.info("select() 호출 : qnaReplyId = " + qnaReplyId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_qna_reply_id", qnaReplyId);
	}

	@Override
	public int update(int replyReplyId, String replyReplyContent) {
		logger.info("update() 호출");
		logger.info("replyReplyId = " + replyReplyId + ", replyReplyContent = " + replyReplyContent);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("replyReplyId", replyReplyId);
		args.put("replyReplyContent", replyReplyContent);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int replyReplyId) {
		logger.info("delete() 호출 : replyReplyId = " + replyReplyId);
		return sqlSession.delete(NAMESPACE + ".delete", replyReplyId);
	}

}
>>>>>>> refs/remotes/origin/feature/VO
