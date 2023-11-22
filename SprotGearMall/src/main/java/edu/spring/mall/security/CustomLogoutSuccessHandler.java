package edu.spring.mall.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.spring.mall.util.CookieUtil;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
	private final Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private OAuth2AuthorizedClientService authService;

	@Autowired
	private ClientRegistrationRepository social;
	


	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("onLogoutSuccess 호출");
		Cookie[] cookies = request.getCookies();
		String registrationId = "";
		if(cookies ==null) {
			logger.info("쿠키없음");
		}
		for (Cookie cookie : cookies) {
			if ("registrationId".equals(cookie.getName())) {
				registrationId = cookie.getValue();
				logger.info("쿠키값 가져오기 : " + registrationId);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
		}
		
		String accessToken;
		if(registrationId.equals("naver")) {
			try {
				accessToken = CookieUtil.getDecryptedCookieValue(request, "Token");
				logger.info("Token" + accessToken);
				JsonNode resultNode = deleteNaverToken(accessToken, registrationId);
				String result = resultNode.path("result").asText();
				if(result.equals("success")) {
					logger.info("oauth 인증토큰 제거 성공");
					authService.removeAuthorizedClient(registrationId, authentication.getName());
					CookieUtil.deleteCookie(request, response, "Token");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			redirectStrategy.sendRedirect(request, response, "/");
			return;
		}//end naver
		
		if(registrationId.equals("google")) {
			try {
				logger.info("구글 토큰 삭제");
				accessToken = CookieUtil.getDecryptedCookieValue(request, "Token");
				logger.info("Token" + accessToken);
				JsonNode resultNode = deleteGoogleToken(accessToken, registrationId);
				CookieUtil.deleteCookie(request, response, "Token");
			} catch (Exception e) {
				e.printStackTrace();
			}
			redirectStrategy.sendRedirect(request, response, "/");
			return;

		}//end google
		redirectStrategy.sendRedirect(request, response, "/");


	}//end onLogoutSuccess

	private JsonNode deleteNaverToken(String accessToken, String registrationId) 
			throws JsonMappingException, JsonProcessingException {
			logger.info("네이버 토큰 삭제 요청");
		ClientRegistration registration = social.findByRegistrationId(registrationId);
		String clientId = registration.getClientId();
		String clientSecret = registration.getClientSecret();
		
		String baseurl = registration.getProviderDetails().getTokenUri();
		String makedurl = UriComponentsBuilder.fromHttpUrl(baseurl)
				.queryParam("grant_type", "delete")
				.queryParam("client_id", clientId)
				.queryParam("client_secret", clientSecret)
				.queryParam("access_token", accessToken)
				.queryParam("service_provider", "NAVER")
				.toUriString();
		logger.info("makedurl : " + makedurl );
		

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "delete");
		params.add("client_id", clientId);
		params.add("client_secret", clientSecret);
		params.add("access_token", accessToken);
		params.add("service_provider", "NAVER");
		logger.info("accessToken : " + accessToken);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(makedurl, requestEntity, String.class);

		// 결과 확인
		String responseBody = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(responseBody);

	}
	
	private JsonNode deleteGoogleToken(String accessToken, String registrationId) 
			throws JsonMappingException, JsonProcessingException {
		logger.info("구글 토큰 삭제 요청");
		logger.info("accessToken : " + accessToken);
		ClientRegistration registration = social.findByRegistrationId(registrationId);
		String clientId = registration.getClientId();
		String clientSecret = registration.getClientSecret();
		String baseurl = registration.getProviderDetails().getTokenUri();
		String url = "https://accounts.google.com/o/oauth2/revoke?token=" + accessToken;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		String responseBody =response.getBody();
		logger.info("response : " + response);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(responseBody);
	}

}
