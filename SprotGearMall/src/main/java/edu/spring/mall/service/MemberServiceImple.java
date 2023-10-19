package edu.spring.mall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.persistence.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {
	private final Logger logger = LoggerFactory.getLogger(MemberServiceImple.class);
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDAO dao;

	@Override
	public int create(MemberVO vo) throws Exception {
		logger.info("create 호출 vo = " + vo.toString());
		
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		logger.info("비밀번호 암호화 성공");
		int result = dao.insert(vo);
		return result;
	}

	@Override
	public MemberVO read(String memberId, String password) throws Exception {
		logger.info("read 호출");
		logger.info("memberId : " + memberId);
		
		return null;
	}

	@Override
	public String read(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(String memberId, MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String MemberId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
