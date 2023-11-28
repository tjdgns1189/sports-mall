package edu.spring.mall.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer{
	private Logger logger = LoggerFactory.getLogger(WebsocketConfig.class);

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(userQueryWebSocketHandler(), "/echo")
        .setAllowedOrigins("*")
        .addInterceptors(new SecurityHandshakeInterceptor());
	}
	
	@Bean
	public UserQueryWebsocketHandler userQueryWebSocketHandler() {
	      return new UserQueryWebsocketHandler();
	    }

}
