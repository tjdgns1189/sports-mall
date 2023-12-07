package edu.spring.mall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.mall.domain.NotificationVO;

@Repository
public class NotificationDAOImple implements NotificationDAO {
	private final Logger logger = LoggerFactory.getLogger(NotificationDAOImple.class);
	private final String NAMESPACE = "edu.spring.mall.NotificationMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	@Override
	public int insert(NotificationVO vo) {
		logger.info("insert 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<NotificationVO> select(String memberId) {
		return sqlSession.selectList(NAMESPACE + ".select_member", memberId);
	}

	@Override
	public List<NotificationVO> selectAdmin(String group) {
		return sqlSession.selectList(NAMESPACE + ".select_group", group);
	}

	@Override
	public int update(NotificationVO vo) {
		logger.info("update");
		return sqlSession.update(NAMESPACE + ".updateDate", vo);
	}

	@Override
	public int delete(NotificationVO vo) {
		logger.info("delete");
		return sqlSession.delete(NAMESPACE + ".delete", vo);
	}

}
