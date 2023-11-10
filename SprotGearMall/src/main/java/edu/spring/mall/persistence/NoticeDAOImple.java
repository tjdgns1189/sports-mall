package edu.spring.mall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.NoticeVO;
import edu.spring.mall.pageutil.PageCriteria;
@Repository
public class NoticeDAOImple implements NoticeDAO {
	
	private final Logger logger= LoggerFactory.getLogger(NoticeDAOImple.class);
	
	private final String NAMESPACE="edu.spring.mall.NoticeMapper";
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(NoticeVO vo) {
		logger.info("insert 호출");
		return sqlSession.insert(NAMESPACE + ".insert",vo);
	}

	@Override
	public List<NoticeVO> select() {
		logger.info("select 호출");
		return sqlSession.selectList(NAMESPACE+ ".selectAll");
	}

	@Override
	public List<NoticeVO> select(String boardTitle) {
		logger.info("select(boardTitle)");
		return sqlSession.selectList(NAMESPACE + ".select_by_title", boardTitle);
	}

	@Override
	public int update(NoticeVO vo) {
		logger.info("update");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int noitceId) {
		logger.info("update");
		return sqlSession.delete(NAMESPACE + ".delete", noitceId);
	}

	@Override
	public NoticeVO select(int noitceId) {
		logger.info("select(boardId");
		return sqlSession.selectOne(NAMESPACE + ".select_detail", noitceId);
	}

	@Override
	public List<NoticeVO> select(PageCriteria criteria) {
		logger.info("select 호출");
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	@Override
	public int count() {
		logger.info("count호출");
		return sqlSession.selectOne(NAMESPACE + ".select_count");
	}

}
