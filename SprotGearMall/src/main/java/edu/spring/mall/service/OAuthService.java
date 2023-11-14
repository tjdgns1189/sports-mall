package edu.spring.mall.service;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import edu.spring.mall.security.CustomUserDetails;

public interface OAuthService {
	//접근 주소 생성
	String createUrl(HttpServletRequest request, String RegistrationId);
	//변조방지
	OAuth2AccessToken getToken(String registrationId, String code, String state) throws JsonMappingException, JsonProcessingException;
	
    JsonNode getUserInfo(String registrationId, OAuth2AccessToken accessToken);

    String generateRandomState();
	
    void securityLogin(CustomUserDetails user);
    
    String buildUrl(ClientRegistration registration, String state);
    
    void saveToken(HttpServletRequest request, HttpServletResponse response, 
    		String registaionId, OAuth2AccessToken accessToken) throws IOException, ServletException;
}
