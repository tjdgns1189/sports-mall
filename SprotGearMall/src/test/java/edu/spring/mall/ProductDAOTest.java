package edu.spring.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.persistence.ProductDAO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class ProductDAOTest {
	private static final Logger logger =
			LoggerFactory.getLogger(ProductDAOTest.class);
	
	@Autowired
	private ProductDAO dao;
	
	@Test
	public void testDAO() {
		testInsert();
	}

	private void testInsert() {
		ProductVO vo = new ProductVO(0, "∞°Ω√πÊ∏¡¿Ã", 80000, 9, "ø§¡ˆ", "¿ÃπÃ¡ˆ∞Ê∑Œ", "πÊ∏¡¿Ã", 1);
		int result = dao.insert(vo);
		logger.info(result + "«‡ ª¿‘");
		
	}
}
