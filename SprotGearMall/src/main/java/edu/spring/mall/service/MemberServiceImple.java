package edu.spring.mall.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

	@Autowired
	private UserDetailsService service;

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
	public int update(Map userDetail) throws Exception {
		int result = 0;
		if (userDetail.containsKey("password")) {
			result = dao.updateUserPassword(userDetail);
			if (result == 1) {
				String memberId = (String) userDetail.get("memberId");
				UserDetails user = service.loadUserByUsername(memberId);
				Authentication newAuth = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
						user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(newAuth);
			}
		} else {
			logger.info("user정보 수정");
			result = dao.updateUserDetail(userDetail);
			if (result == 1) {
				String memberId = (String) userDetail.get("memberId");
				UserDetails user = service.loadUserByUsername(memberId);
				Authentication newAuth = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
						user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(newAuth);

			}

		}
		return result; // 실제로는 수정된 행의 개수나 다른 적절한 값을 반환하면 됩니다.
	}

	@Override
	public int delete(String MemberId) throws Exception {
		logger.info("delete호출");
		int result = dao.delete(MemberId);
		
		return result;
	}

}
