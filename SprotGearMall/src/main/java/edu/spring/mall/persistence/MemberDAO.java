package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.MemberVO;

public interface MemberDAO {
	int insert(MemberVO vo);
	List<MemberVO> select();
	int update(MemberVO vo);
	int delete(String memberId);
}
