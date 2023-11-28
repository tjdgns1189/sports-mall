package edu.spring.mall.websocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class UserQueryWebsocketHandler extends TextWebSocketHandler {
	private final Logger logger = LoggerFactory.getLogger(UserQueryWebsocketHandler.class);
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();

	
	//웹소켓 연결시 
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SecurityContext securityContext = (SecurityContext) session.getAttributes().get("SPRING_SECURITY_CONTEXT");
        if (securityContext != null) {
            Authentication auth = securityContext.getAuthentication();
            String username = auth.getName();
            session.getAttributes().put("username", username);
            logger.info("id : " + username + " 접속");
        }
        sessionList.add(session);
	}
	
	//메세지 전송시
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String username = (String) session.getAttributes().get("username");
		logger.info(username + " : " + message.getPayload());

		 for(WebSocketSession sess : sessionList){
	           sess.sendMessage(new TextMessage(username +" : "+ message.getPayload()));
	        }
	}
	
	//웹소켓 종료
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("id : " + session.getId() + "의 연결 종료");
        sessionList.remove(session);

	}
}
