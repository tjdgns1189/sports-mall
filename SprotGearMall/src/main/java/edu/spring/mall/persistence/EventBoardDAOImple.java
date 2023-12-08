package edu.spring.mall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.EventBoardVO;
import edu.spring.mall.pageutil.PageCriteria;


@Repository
public class EventBoardDAOImple implements EventBoardDAO {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(EventBoardDAOImple.class);
	
	private static final String NAMESPACE =
			"edu.spring.mall.EventBoardMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(EventBoardVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<EventBoardVO> select(PageCriteria criteria) {
		logger.info("select() 호출");
		
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts()");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}

	@Override
	public EventBoardVO select(int eventBoardId) {
		logger.info("select() 호출 : eventBoardId = " + eventBoardId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_event_board_id", eventBoardId);
	}

	@Override
	public int update(EventBoardVO vo) {
		logger.info("update() 호출 : vo = " + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int eventBoardId) {
		logger.info("delete() 호출 : qnaBoardId = " + eventBoardId);
		return sqlSession.delete(NAMESPACE + ".delete", eventBoardId);
	}

}
