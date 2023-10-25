package edu.spring.mall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.QnaReplyVO;

@Repository
public class QnaReplyDAOImple implements QnaReplyDAO {
	private static final Logger logger =
			LoggerFactory.getLogger(QnaReplyDAOImple.class);

	private static final String NAMESPACE =
			"edu.spring.mall.QnaReplyMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(QnaReplyVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<QnaReplyVO> select(int qnaBoardId) {
		logger.info("select() 호출 : qnaBoardId = " + qnaBoardId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_qna_board_id", qnaBoardId);
	}

	@Override
	public int update(int qnaReplyId, String qnaReplyContent) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int qnaReplyId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
