package edu.spring.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.mall.domain.QnaBoardVO;
import edu.spring.mall.persistence.QnaBoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class QnaBoardDAOTest {
	private static final Logger logger =
			LoggerFactory.getLogger(QnaBoardDAOTest.class);
	
	@Autowired
	private QnaBoardDAO dao;
	
	@Test
	public void testDAO() {
		testInsert();
	}

	private void testInsert() {
		QnaBoardVO vo = new QnaBoardVO(0, "test", "변경후", "ㅇㅇㅇㅇ", null);
		int result = dao.insert(vo);
		logger.info(result + "행 삽입");
		
		
	}
}
