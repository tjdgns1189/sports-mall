package edu.spring.mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.domain.OrdersProductJoinVO;

@Service
public class AdminServiceImple implements AdminService {
	private Logger logger = LoggerFactory.getLogger(AdminServiceImple.class);

	@Autowired
	private MemberService memberService;

	@Autowired
	private OrderService orderService;

	@Override
	public List<MemberVO> read() {
		logger.info("read호출");
		return memberService.read();
	}

	@Override
	public MemberVO read(String memberId) {
		logger.info("read 호출 (디테일)");
		return memberService.readDetail(memberId);
	}

	@Override
	public List<MemberVO> readSearch(String memberId) {
		logger.info("read 호출 (검색)");
		return memberService.read(memberId);
	}

	@Override
	public int delete(String memberId) throws Exception {
		logger.info("delete 호출");
		return memberService.delete(memberId);
	}

	@Override
	public List<OrdersProductJoinVO> orderRead(String memberId) throws Exception {
		logger.info("orderRead");
		return orderService.read(memberId);
	}

}
