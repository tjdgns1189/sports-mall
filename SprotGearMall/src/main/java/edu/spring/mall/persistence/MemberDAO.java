package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.security.CustomUserDetails;

public interface MemberDAO {
	int insert(MemberVO vo);
	List<MemberVO> select();
	int select(String memberId);
	CustomUserDetails login(String memberId);
	int update(MemberVO vo);
	int delete(String memberId);
}
