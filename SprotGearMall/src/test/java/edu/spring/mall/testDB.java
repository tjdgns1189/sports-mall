package edu.spring.mall;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.persistence.MemberDAO;

public class testDB {
	private static final Logger logger = LoggerFactory.getLogger(testDB.class);
	
	@Autowired
	private static MemberDAO dao;
	
	@Test
	public void daoTest() {
		
		insert();
	}

	private void insert() {
		logger.info("insert호출");
		MemberVO vo = new MemberVO("test", "1234","홍길동", "010-1234-5678", "test@test.com","서울특별시 성북구 성북로21길", "관리자");
		dao.insert(vo);
	}

}
