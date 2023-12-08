package edu.spring.mall.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.EventReplyVO;
import edu.spring.mall.service.EventReplyService;

@RestController
@RequestMapping(value="event")
public class EventReplyRestController {
	private static final Logger logger =
			LoggerFactory.getLogger(EventReplyRestController.class);
	
	@Autowired
	private EventReplyService service;
	
	@PostMapping("/replies")
	public ResponseEntity<Integer> repliesPost(@RequestBody EventReplyVO vo, Principal principal) {
		logger.info("repliesPost() 호출");

		String memberId = principal.getName();
		vo.setMemberId(memberId);
		logger.info("vo : " + vo.toString());
		int result = service.create(vo);
		return new ResponseEntity<Integer>(result,HttpStatus.OK);  
	}
	
	@GetMapping("/replies")
    public ResponseEntity<List<EventReplyVO>> getReplies(int eventBoardId) {
        List<EventReplyVO> replies = service.read(eventBoardId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        for (EventReplyVO reply : replies) {
            reply.setUserGrade(isAdmin ? "ROLE_ADMIN" : "ROLE_USER");
        }
        
        return new ResponseEntity<List<EventReplyVO>>(replies, HttpStatus.OK);
    }
	
	@DeleteMapping("/replies")
	public ResponseEntity<Integer> deleteReplies(@RequestBody EventReplyVO vo) {
		logger.info("deleteReplies 호출");
		int result = service.delete(vo.getEventReplyId());
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@PutMapping("/replies")
	public ResponseEntity<Integer> updateReplies(@RequestBody EventReplyVO vo  ) {
		logger.info("updateReplies 호출");
		int result = service.update(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
