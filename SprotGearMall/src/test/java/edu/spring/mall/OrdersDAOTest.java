package edu.spring.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.persistence.MemberDAO;
import edu.spring.mall.persistence.OrdersDAO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class OrdersDAOTest {
	private static final Logger logger =
			LoggerFactory.getLogger(OrdersDAOTest.class);
	
	@Autowired
	private OrdersDAO dao;
	
	@Test
	public void testDAO() {
		testInsert();
	}

	private void testInsert() {
		OrdersVO vo = new OrdersVO(0, "hoon", 2, 6000000, 4, null);
		int result = dao.insert(vo);
		logger.info(result + "Çà »ðÀÔ");
		
		
	}
}
