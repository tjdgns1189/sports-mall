package edu.spring.mall.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class AjaxAwareAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public AjaxAwareAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "접근권한 없음");
		} else {
			super.commence(request, response, authException);
		}

	}
}
