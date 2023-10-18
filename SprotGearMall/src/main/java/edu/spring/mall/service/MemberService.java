package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.MemberVO;


public interface MemberService {
	int create(MemberVO vo) throws Exception;
	MemberVO read(String memberId, String password)throws Exception;
	String read(String memberId);
	int update(String memberId, MemberVO vo)throws Exception;
	int delete(String MemberId) throws Exception;

}
