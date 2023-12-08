package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.spring.mall.domain.NoticeVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.pageutil.PageMaker;
import edu.spring.mall.service.NoticeService;

@Controller
@RequestMapping(value="notice")
public class NoticeController {
	private final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService service;
	
	
	@GetMapping("/noticeboard")
	public void noticeGet(Model model,Integer page, Integer numsPerPage) {
		logger.info("noticeGet호출");
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		List<NoticeVO> list = service.read(criteria);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(service.getTotalCount());
		pageMaker.setPageData();
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@GetMapping("/register")
	public void noticeRegisterGET() {
		logger.info("registerGET 호출");
	}
	
	@PostMapping("/register")
	public String noticeRegisterPOST(@RequestParam String noticeTitle,
			@RequestParam String noticeContent) {
		logger.info("noticeRegisterPOST 호출");
		NoticeVO vo = new NoticeVO(noticeTitle, noticeContent);
		service.create(vo);
		
		return "redirect:/notice/noticeboard";
		
	}
	
	@GetMapping("/detail")
	public void noticeDetail(int noticeId, Model model) {
		logger.info("noticeDetailGet 호출");
		NoticeVO vo = service.read(noticeId);
		model.addAttribute("vo", vo);
		
	}
	
	@GetMapping("/update")
	public String noticeUpdate(Model model, int noticeId) {
		logger.info("update 호출");
		NoticeVO vo = service.read(noticeId);
		model.addAttribute("vo",vo);
		return "notice/register";
	}
	
	
	@PostMapping("/update")
	public String noticeUpdate(@RequestParam String noticeTitle,
			@RequestParam String noticeContent,
			@RequestParam int noticeId) {
		logger.info("noticeUpdate 호출");
		NoticeVO vo = new NoticeVO(noticeId, noticeTitle, noticeContent);
		int result = service.update(vo);
		logger.info("업데이트 후 " + result);
		return "redirect:/notice/detail?noticeId=" + noticeId;

	}
	
	@PostMapping("/delete")
	public String noticeDelete(int noticeId) {
		logger.info("noticeRegisterPOST 호출");
		service.delete(noticeId);
		return "redirect:/notice/noticeboard";
	}
	
}
