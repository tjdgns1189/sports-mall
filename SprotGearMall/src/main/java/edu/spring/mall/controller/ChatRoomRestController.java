package edu.spring.mall.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.ChatRoomVO;
import edu.spring.mall.service.ChatRoomService;

@RestController
public class ChatRoomRestController {
	private Logger logger = LoggerFactory.getLogger(ChatRoomRestController.class);

	@Autowired
	private ChatRoomService service;

	@GetMapping("/admin/rooms")
	public ResponseEntity<Collection<ChatRoomVO>> roomListGET() {
		Collection<ChatRoomVO> rooms = service.getAllChatList();
		logger.info("채팅 리스트 호출 : " + rooms.size() + "개의 채팅방이 있음");
		return ResponseEntity.ok(rooms);

	}

}
