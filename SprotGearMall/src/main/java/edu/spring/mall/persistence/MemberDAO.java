package edu.spring.mall.persistence;

import java.util.List;
import java.util.Map;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.security.CustomUserDetails;

public interface MemberDAO {
	int insert(MemberVO vo);
	List<MemberVO> select();
	List<MemberVO> selectById(String memberId);
	MemberVO selectDetail(String memberId);
	int select(String memberId);
	CustomUserDetails login(String memberId);
	int update(MemberVO vo);
	int updateUserDetail(Map user);
	int updateUserPassword(Map user);
	int delete(String memberId);
}
