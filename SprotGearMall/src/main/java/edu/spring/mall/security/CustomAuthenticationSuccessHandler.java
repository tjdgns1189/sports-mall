package edu.spring.mall.security;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
		if (session != null) {
			SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
			if (savedRequest != null) {
				logger.info("원래 접근하려는 uri 접근");
				String targetURL = savedRequest.getRedirectUrl();
				redirectStrategy.sendRedirect(request, response, targetURL);
				return;
			}

			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("targeturl".equals(cookie.getName())) {
						logger.info("헤더 로그인 호출");
						String decodedUrl = URLDecoder.decode(cookie.getValue(), "UTF-8");
						logger.info("targetURL : " + decodedUrl);
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
						redirectStrategy.sendRedirect(request, response, decodedUrl);
						return;
					}
				}
			}
			redirectStrategy.sendRedirect(request, response, "/index");
		}
	}
}
