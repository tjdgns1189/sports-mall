package edu.spring.mall.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
	private final Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

	@Autowired
	private OAuth2AuthorizedClientService authService;

	@Autowired
	private ClientRegistrationRepository social;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("onLogoutSuccess 호출");
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("/mall");
			return;
		}
		String registrationId = (String) session.getAttribute("registrationId");
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthorizedClient authorizedClient = authService.loadAuthorizedClient(registrationId, principal.getName());
		String accessToken = authorizedClient.getAccessToken().getTokenValue();
		deleteNaverToken(accessToken, registrationId);
		session.removeAttribute("registrationId");
		authService.removeAuthorizedClient(registrationId, principal.getName());
		response.sendRedirect("/mall");

	}

	private void deleteNaverToken(String accessToken, String registrationId) {
		ClientRegistration registration = social.findByRegistrationId(registrationId);
		String clientId = registration.getClientId();
		String clientSecret = registration.getClientSecret();
		String url = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=" + clientId + "&client_secret="
				+ clientSecret + "&access_token=" + accessToken + "&service_provider=NAVER";
		String baseurl = "https://nid.naver.com/oauth2.0/token";
		String makedurl = UriComponentsBuilder.fromHttpUrl(baseurl)
				.queryParam("grant_type", "delete")
				.queryParam("client_id", clientId)
				.queryParam("client_secret", clientSecret)
				.queryParam("access_token", accessToken)
				.queryParam("service_provider", "NAVER")
				.toUriString();
		logger.info("makedurl");
		logger.info("url");
		if(url.equals(makedurl)) {
			logger.info("만들어준 주소가 동일함");
		}

		Map<String, String> params = new HashMap<>();
		params.put("grant_type", "delete");
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		params.put("access_token", accessToken);
		params.put("service_provider", "NAVER");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(params, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

		// 결과 확인
		String responseBody = response.getBody();
		System.out.println(responseBody);
	}

}
