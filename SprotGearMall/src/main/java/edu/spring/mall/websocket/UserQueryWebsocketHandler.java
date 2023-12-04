package edu.spring.mall.websocket;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.nimbusds.jose.shaded.json.JSONObject;

import edu.spring.mall.service.ChatRoomService;

public class UserQueryWebsocketHandler extends TextWebSocketHandler {
	private final Logger logger = LoggerFactory.getLogger(UserQueryWebsocketHandler.class);

	@Autowired
	private ChatRoomService service;

	
	//웹소켓 연결시 
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("소켓 연결");
        SecurityContext securityContext = (SecurityContext) session.getAttributes().get("SPRING_SECURITY_CONTEXT");
        if (securityContext != null) {
            Authentication auth = securityContext.getAuthentication();
            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
			String username = auth.getName();

			if(isAdmin) {
				String roomId = extractRoomIdFromSession(session);
            	logger.info("관리자 접속 : " + username + " || 채팅방 번호 : " + roomId);
				session.getAttributes().put("username", username);
				service.joinRoom(roomId, session);
			}else{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				String dateTime = dateFormat.format(new Date());
				String roomId = dateTime + "-" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
				session.getAttributes().put("username", username);
				service.create(roomId, session);
				logger.info("채팅번호 : " + roomId + " || id : " + username + " 접속");
				}
            }//end if

        }//end afterConnectionEstablished()

	
	//메세지 전송시
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	    String username = (String) session.getAttributes().get("username");
	    String senderType = session.getAttributes().containsKey("ROLE_ADMIN") ? "admin" : "user";
	    ChatRoom room = service.getChatRoom(session);

	    if (room == null) {
	        logger.error("채팅방을 찾을 수 없음");
	        return;
	    }

	    JSONObject jsonMessage = new JSONObject();
	    jsonMessage.put("username", username);
	    jsonMessage.put("senderType", senderType);
	    jsonMessage.put("message", message.getPayload());
	    jsonMessage.put("timestamp", new Date().getTime());

	    TextMessage formattedMessage = new TextMessage(jsonMessage.toString());

	    // 채팅방의 모든 참가자에게 메시지 전송
	    for (WebSocketSession x : room.getJoinUser()) {
	        if (x.isOpen()) {
	            x.sendMessage(formattedMessage);
	        }
	    }


	}//end handleTextMessage()
	
	//웹소켓 종료
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String username = (String) session.getAttributes().get("username");
	    ChatRoom room = service.getChatRoom(session);
		logger.info("id : " + username + "의 연결 종료");
        service.removeChatRoom(room.getRoomId(), session);

	}

	private String extractRoomIdFromSession(WebSocketSession session) {
		URI uri = session.getUri();
		if (uri != null) {
			String query = uri.getQuery();
			if (query != null) {
				String[] params = query.split("&");
				for (String param : params) {
					String[] keyValue = param.split("=");
					if ("roomId".equals(keyValue[0]) && keyValue.length > 1) {
						return keyValue[1];
					}
				}
			}
		}
		return null;
	}
	

}