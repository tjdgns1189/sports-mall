package edu.spring.mall.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("인증성공 핸들러 호출(CustomAuthenticationSuccessHandler)");
		HttpSession session = request.getSession(false);
		if(session != null) {
			SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
			
			if(savedRequest != null) {
				String targetURL = savedRequest.getRedirectUrl();
				redirectStrategy.sendRedirect(request, response, targetURL);
				return;
			}	
			
	        redirectStrategy.sendRedirect(request, response, "/index");

		}
	}

}
