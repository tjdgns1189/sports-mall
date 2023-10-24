package edu.spring.mall.service;

import java.util.Map;

import edu.spring.mall.domain.MemberVO;


public interface MemberService {
	int create(MemberVO vo) throws Exception;
	MemberVO read(String memberId, String password)throws Exception;
	String read(String memberId);
	int update(Map userDetail)throws Exception;
	int delete(String MemberId) throws Exception;

}
