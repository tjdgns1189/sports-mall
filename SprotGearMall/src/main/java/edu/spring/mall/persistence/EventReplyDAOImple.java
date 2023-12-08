package edu.spring.mall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.EventReplyVO;


@Repository
public class EventReplyDAOImple implements EventReplyDAO {

	private static final Logger logger =
			LoggerFactory.getLogger(QnaReplyDAOImple.class);

	private static final String NAMESPACE =
			"edu.spring.mall.EventReplyMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public int insert(EventReplyVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int update(EventReplyVO vo) {
		logger.info("update() 호출");
		return sqlSession.update(NAMESPACE + ".update",vo);
	}

	@Override
	public int delete(int eventReplyId) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete" , eventReplyId);
	}

	@Override
	public List<EventReplyVO> select(int eventBoardId) {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all_by_event_board_id",eventBoardId);
	}

}
