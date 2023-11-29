package edu.spring.mall.websocket;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import edu.spring.mall.service.ChatRoomService;

public class UserQueryWebsocketHandler extends TextWebSocketHandler {
	private final Logger logger = LoggerFactory.getLogger(UserQueryWebsocketHandler.class);
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	
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
            if(isAdmin) {
            	logger.info("관리자 접속");
            	 URI uri = session.getUri();
            	    if (uri != null) {
            	        String path = uri.getPath();
            	        String roomId = extractRoomIdFromPath(path);
            	        logger.info("roomId : " + roomId);
            }
            }
            String username = auth.getName();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String dateTime = dateFormat.format(new Date());
            String roomId = dateTime + "-" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
            session.getAttributes().put("username", username);
            service.create(roomId, session);
            logger.info("채팅번호 : " + roomId + " || id : " + username + " 접속");
        }
	}
	
	//메세지 전송시
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String username = (String) session.getAttributes().get("username");
		logger.info(username + " : " + message.getPayload());
		// message.getPayload()이게 전송받은 메세지임
	
	
	}
	
	//웹소켓 종료
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String username = (String) session.getAttributes().get("username");
		logger.info("id : " + username + "의 연결 종료");
        sessionList.remove(session);

	}
	
	private String extractRoomIdFromPath(String path) {
	    // 경로를 "/" 기준으로 분할
	    String[] pathSegments = path.split("/");

	    // 마지막 세그먼트가 roomId임을 가정
	    if (pathSegments.length > 0) {
	        return pathSegments[pathSegments.length - 1];
	    }

	    return null; 
	}
	

}
