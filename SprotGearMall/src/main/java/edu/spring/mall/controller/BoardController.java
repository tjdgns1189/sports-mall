package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.mall.domain.QnaBoardVO;
import edu.spring.mall.qnapageutil.PageCriteria;
import edu.spring.mall.qnapageutil.PageMaker;
import edu.spring.mall.qnaservice.QnaBoardService;

@Controller
@RequestMapping(value = "/qnaBoard")
public class BoardController {
	private static final Logger logger =
			LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private QnaBoardService qnaBoardService;
	
	@GetMapping("/qnaBoard")
	public void qnaBoardGET(Model model, Integer page, Integer numsPerPage) {
		logger.info("qnaBoardGET() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		List<QnaBoardVO> list = qnaBoardService.read(criteria);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(qnaBoardService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@GetMapping("/qnaDetail")
	public void detail(Model model, Integer qnaBoardId, Integer page) {
		logger.info("detail() 호출 : qnaBoardId = " + qnaBoardId);
		QnaBoardVO vo = qnaBoardService.read(qnaBoardId);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		
		
	} // end detail()
}












