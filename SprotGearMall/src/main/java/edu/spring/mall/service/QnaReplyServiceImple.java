package edu.spring.mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.service.QnaReplyServiceImple;
import edu.spring.mall.domain.QnaBoardVO;
import edu.spring.mall.domain.QnaReplyVO;
import edu.spring.mall.persistence.QnaReplyDAO;
import edu.spring.mall.pageutil.PageCriteria;

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
		logger.info(resultInsert + "행 삽입");
		return 1;
	}

	@Override
	public List<QnaReplyVO> read(int qnaBoardId) {
		logger.info("read() 호출 : qnaBoardId = " + qnaBoardId);
		return qnaReplyDAO.select(qnaBoardId);
	}

	@Override
	public int update(int qnaReplyId, String qnaReplyContent) {
		logger.info("update() 호출");
		logger.info("qnaReplyId = " + qnaReplyId + ", qnaReplyContent = " + qnaReplyContent);
		return qnaReplyDAO.update(qnaReplyId, qnaReplyContent);
	}

	@Override
	public int delete(int qnaReplyId) throws Exception {
		logger.info("delete() 호출 : qnaReplyId = " + qnaReplyId);
		return qnaReplyDAO.delete(qnaReplyId);
	}

}