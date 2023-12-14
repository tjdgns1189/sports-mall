package edu.spring.mall.service;

import java.util.List;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.domain.OrdersProductJoinVO;

public interface AdminService {
	// 유저 전체 목록 호출
	List<MemberVO> read();

	// 유저 단일 목록 호출
	MemberVO read(String memberId);

	// 유저 검색
	List<MemberVO> readSearch(String memberId);

	// 관리자의 유저 삭제
	int delete(String memberId) throws Exception;

	// 해당 유저의 주문 기록확인
	List<OrdersProductJoinVO> orderRead(String memberId) throws Exception;

}
