package edu.spring.mall.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);
	private String loginFormUrl; // 로그인 페이지 URL

	public CustomAuthenticationEntryPoint(String loginFormUrl) {
		this.loginFormUrl = loginFormUrl;
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.info("commence 호출");

		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "접근권한 없음");
		} else {
			if (authException != null) {
				request.getSession().setAttribute("authError", "ACCESS_DENIED");
			}
			response.sendRedirect(loginFormUrl);
		}
	}
}
