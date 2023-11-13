package edu.spring.mall.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.oauth2.client.registration.ClientRegistration;

import com.fasterxml.jackson.databind.JsonNode;

import edu.spring.mall.security.CustomUserDetails;

public interface OAuthService {
	//접근 주소 생성
	String createUrl(HttpServletRequest request, String RegistrationId);
	//변조방지
	String getToken(String registrationId, String code, String state);
	
    JsonNode getUserInfo(String registrationId, String accessToken);

    String generateRandomState();
	
    void securityLogin(CustomUserDetails user);
    
    String buildUrl(ClientRegistration registration, String state);
}
