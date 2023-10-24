package edu.spring.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.mall.domain.QnaReplyVO;
import edu.spring.mall.persistence.QnaReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class QnaReplyDAOTest {
	private static final Logger logger =
			LoggerFactory.getLogger(QnaReplyDAOTest.class);
	
	@Autowired
	private QnaReplyDAO dao;
	
	@Test
	public void testDAO() {
		testInsert();
	}

	private void testInsert() {
		QnaReplyVO vo = new QnaReplyVO(0, 1, "test", "六六六六", null);
		int result = dao.insert(vo);
		logger.info(result + "ч 鳶殮");
		
		
	}
}
