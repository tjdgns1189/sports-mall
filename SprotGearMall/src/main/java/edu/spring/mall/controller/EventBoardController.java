package edu.spring.mall.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.mall.domain.EventBoardVO;
import edu.spring.mall.domain.NoticeVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.pageutil.PageMaker;
import edu.spring.mall.service.EventBoardService;

@Controller
@RequestMapping(value = "event")
public class EventBoardController {
	private static final Logger logger = LoggerFactory.getLogger(EventBoardController.class);

	@Autowired
	private EventBoardService eventBoardService;

	@GetMapping("/eventboard")
	public void eventBoardGet(Model model, Integer page, Integer numsPerPage) {
		logger.info("eventBoardGet() 호출");
		PageCriteria criteria = new PageCriteria();

		if (page != null) {
			criteria.setPage(page);
		}

		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<EventBoardVO> list = eventBoardService.read(criteria);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(eventBoardService.getTotalCounts());
		pageMaker.setPageData();

		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
	}

	@GetMapping("/register")
	public void registerGet(Model model) {
		logger.info("register()호출");

	}

	@PostMapping("/register")
	public String eventBoardRegisterPOST(Model model, RedirectAttributes reAttr, @RequestParam String eventBoardTitle,
			@RequestParam String eventBoardContent, Principal principal) {
		logger.info("eventBoardRegisterPOST() 호출");
		String memberId = principal.getName();

		EventBoardVO vo = new EventBoardVO(0, memberId, eventBoardTitle, eventBoardContent, null, 0);
		int result = eventBoardService.create(vo);
		if (result == 1) {
			logger.info("상품등록 성공");
			reAttr.addFlashAttribute("insert_result", "success");
		}
		return "redirect:/event/eventboard";

	}

	@GetMapping("/detail")
	public void eventBoardDetailGet(Model model, Principal principal, int eventBoardId) {
		logger.info("eventBoardDetailGet() 호출");
		EventBoardVO vo = eventBoardService.read(eventBoardId);
		if (principal != null && principal.getName() != null) {
		    String memberId = principal.getName();
		    model.addAttribute("currentUserMemberId", memberId);
		}
		if(vo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		model.addAttribute("vo", vo);

	}

	@GetMapping("/update")
	public String eventBoardUpdate(Model model, int eventBoardId) {
		logger.info("eventBoardUpdate() 호출");
		EventBoardVO vo = eventBoardService.read(eventBoardId);
		model.addAttribute("vo", vo);
		return "event/register";
	}

	@PostMapping("/update")
	public String eventBoardUpdate(@RequestParam String eventBoardTitle, @RequestParam String eventBoardContent,
			@RequestParam int eventBoardId) {
		logger.info("eventBoardUpate() 호출");

		EventBoardVO vo = new EventBoardVO(eventBoardId, null, eventBoardTitle, eventBoardContent, null);

		eventBoardService.update(vo);

		return "redirect:/event/detail?eventBoardId=" + eventBoardId;
	}

	@PostMapping("/delete")
	public String evnetBoardDelete(@RequestParam int eventBoardId) {
		logger.info("eventBoardDelete() 호출");
		eventBoardService.delete(eventBoardId);

		return "redirect:/event/eventboard";
	}

}
