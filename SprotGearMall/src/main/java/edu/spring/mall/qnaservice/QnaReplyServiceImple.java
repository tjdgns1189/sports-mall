<<<<<<< HEAD
package edu.spring.mall.qnaservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.qnaservice.QnaReplyServiceImple;
import edu.spring.mall.domain.QnaBoardVO;
import edu.spring.mall.domain.QnaReplyVO;
import edu.spring.mall.persistence.QnaReplyDAO;
import edu.spring.mall.qnapageutil.PageCriteria;

@Service
public class QnaReplyServiceImple implements QnaReplyService {
	private static final Logger logger = 
			LoggerFactory.getLogger(QnaReplyServiceImple.class);

	@Autowired
	private QnaReplyDAO qnaReplyDAO;

	@Override
	public int create(QnaReplyVO vo) throws Exception {
		logger.info("create() 호출 : vo = " + vo.toString());
		int resultInsert = qnaReplyDAO.insert(vo);
		logger.info(resultInsert + " 행 댓글 입력 성공");
		return 1;
	}

	@Override
	public List<QnaReplyVO> read(int qnaBoardId) {
		logger.info("read() 호출 : qnaBoardId = " + qnaBoardId);
		return qnaReplyDAO.select(qnaBoardId);
	}

	@Override
	public int update(int qnaReplyId, String qnaReplyContent) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int qnaReplyId, int qnaBoardId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
=======
package edu.spring.mall.qnaservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.qnaservice.QnaReplyServiceImple;
import edu.spring.mall.domain.QnaBoardVO;
import edu.spring.mall.domain.QnaReplyVO;
import edu.spring.mall.persistence.QnaReplyDAO;
import edu.spring.mall.qnapageutil.PageCriteria;

@Service
public class QnaReplyServiceImple implements QnaReplyService {
	private static final Logger logger = 
			LoggerFactory.getLogger(QnaReplyServiceImple.class);

	@Autowired
	private QnaReplyDAO qnaReplyDAO;

	@Override
	public int create(QnaReplyVO vo) throws Exception {
		logger.info("create() 호출 : vo = " + vo.toString());
		int resultInsert = qnaReplyDAO.insert(vo);
		logger.info(resultInsert + " 행 댓글 입력 성공");
		return 1;
	}

	@Override
	public List<QnaReplyVO> read(int qnaBoardId) {
		logger.info("read() 호출 : qnaBoardId = " + qnaBoardId);
		return qnaReplyDAO.select(qnaBoardId);
	}

	@Override
	public int update(int qnaReplyId, String qnaReplyContent) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int qnaReplyId, int qnaBoardId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
>>>>>>> refs/remotes/origin/develop
}