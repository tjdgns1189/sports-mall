package edu.spring.mall.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import edu.spring.mall.domain.ChatRoomVO;
import edu.spring.mall.domain.NotificationVO;
import edu.spring.mall.websocket.ChatRoom;

@Service
public class ChatRoomServiceImple implements ChatRoomService {
	private Logger logger = LoggerFactory.getLogger(ChatRoomServiceImple.class);
	//이건 채팅방의 정보를 가지고 있는 객체 모음
    private Map<String, ChatRoom> chatList = new ConcurrentHashMap<>();
    //이건 매핑을 위한 자료 구조
    private Map<WebSocketSession, ChatRoom> chatMapping = new ConcurrentHashMap<>();
    
    @Autowired
    private NotificationService notification;



	@Override
	public ChatRoom create(String roomId, WebSocketSession userSession) {
	    String username = (String) userSession.getAttributes().get("username");
	    logger.info("채팅방 생성 username : " + username);

		//중복확인
	    for (ChatRoom room : chatList.values()) {
	        WebSocketSession existingSession = room.getUserSession();
	        String existingUsername = (String) existingSession.getAttributes().get("username");

	        if (username.equals(existingUsername)) {
	            logger.info("사용자가 이미 채팅방에 참여 중, 사용자 이름: " + username);
	            return room; // 이미 존재하는 채팅방 반환
	        }
	    }

		logger.info("중복이 아님");
		ChatRoom room = new ChatRoom(roomId, userSession, null, new ArrayList<>(), new Date());
		chatList.put(roomId, room);
		chatMapping.put(userSession, room);
		String groupId = "ROLE_ADMIN";
		String notificationType ="채팅 문의";
		String content = "사용자가 1:1 채팅문의를 요청했습니다";
		String targetUrl = "/mall/admin/chat-list";
		NotificationVO vo = new NotificationVO(groupId, notificationType, content, targetUrl, null, 0);
		notification.create(vo);
		return room;
	}
	
	@Override
	public Collection<ChatRoomVO> getAllChatList() {
		logger.info("memberId 호출");
	    Collection<ChatRoom> rooms = chatList.values();
	    List<ChatRoomVO> roomList = new ArrayList<>();
	    for (ChatRoom room : rooms) {
	    	String username = (String) room.getUserSession().getAttributes().get("username");
	        ChatRoomVO roomVo = new ChatRoomVO(room,username);
	        roomList.add(roomVo);
	    }

	    return roomList;
	}

	@Override
	public void joinRoom(String roomId, WebSocketSession adminSession) {
		logger.info("채팅방 입장: roomId=" + roomId);
	    ChatRoom room = chatList.get(roomId);
		//채팅방 증발시
	    if(room == null) {
			logger.info("채팅방이 없음");
			return;
		}
		
		//관리자 중복입장 방지
		if(room.getAdminSession() != null) {
			return;
		}
		room.setAdminSession(adminSession);
		chatMapping.put(adminSession, room);
	}

	@Override
	public ChatRoom getChatRoom(String roomId) {
		logger.info("getChatRoom 호출");
	    return chatList.get(roomId);
	}

	public ChatRoom getChatRoom(WebSocketSession session) {
		logger.info("getChatRoom 호출");
	    return chatMapping.get(session);
	}

	public void removeChatRoom(String roomId, WebSocketSession session) {
		logger.info("removeChatRoom 호출");
		chatList.remove(roomId);
		chatMapping.remove(session);
	}
	
	public boolean isUserAlreadyInChatRoom(String username) {
		logger.info("isUserAlreadyInChatRoom");
	    return chatMapping.containsKey(username);
	}
	
	

}