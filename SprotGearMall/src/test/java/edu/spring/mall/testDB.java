package edu.spring.mall;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })

@WebAppConfiguration
public class testDB {
	private static final Logger logger = LoggerFactory.getLogger(testDB.class);

	@Autowired
	private MemberDAO dao;

	@Test
	public void daoTest() {

//		insert();
//		select();
//		update();
//		delete();
	}

	private void delete() {
		logger.info("delete 호출");
		String str = "test";
		int result = dao.delete(str);
		logger.info(result + " 행 삭제");
		
	}

	private void update() {
		logger.info("update 호출");
		MemberVO vo = new MemberVO("test", "4321", "둘리", "010-9876-5678", "admin@gmail.com", "경기도", "일반유저");
		logger.info("update 전");
		
		try {
			int result = dao.update(vo);
			logger.info("result = " + result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		


	}

	private void select() {
		logger.info("select 호출");
		List<MemberVO> vo = dao.select();
		for (MemberVO x : vo) {
			logger.info(x.toString());
		}
	}

	private void insert() {
		MemberVO vo = new MemberVO("test", "1234", "홍길동", "010-1234-5678", "test@test.com", "서울특별시 성북구 성북로21길", "관리자");
		logger.info("insert호출");

		dao.insert(vo);

		logger.info("insert호출 후");
	}

}
