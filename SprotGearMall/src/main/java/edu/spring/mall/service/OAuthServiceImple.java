package edu.spring.mall.service;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.spring.mall.security.CustomUserDetails;
@Service
public class OAuthServiceImple implements OAuthService {
	private final Logger logger = LoggerFactory.getLogger(OAuthServiceImple.class);

	
	@Autowired
    private ClientRegistrationRepository social;
	
	@Override
	public String createUrl(HttpServletRequest request, String RegistrationId) {
		logger.info("url 생성 : " + RegistrationId);
        ClientRegistration registration= social.findByRegistrationId(RegistrationId);
        
        if(registration ==null) {
        	return  "false";
        }
        
        String state = generateRandomState();
        request.getSession().setAttribute(RegistrationId + "OAuthState", state);  
        
		return buildUrl(registration, state);
	}

	@Override
	public String getToken(String registrationId, String code, String state) {
		logger.info("getToken 호출");
		 ClientRegistration registration = social.findByRegistrationId(registrationId);

		    RestTemplate restTemplate = new RestTemplate();
		    String tokenEndpoint = registration.getProviderDetails().getTokenUri();

		    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    params.add("grant_type", "authorization_code");
		    params.add("client_id", registration.getClientId());
		    params.add("client_secret", registration.getClientSecret());
		    params.add("code", code);
		    params.add("state", state);

		    ResponseEntity<String> response = restTemplate.postForEntity(tokenEndpoint, params, String.class);
		    return response.getBody();
		
	}

	@Override
	public JsonNode getUserInfo(String registrationId, String accessToken) {
		logger.info("getUserInfo 호출");
		 ClientRegistration registration = social.findByRegistrationId(registrationId);
		 String userInfoEndpoint = registration.getProviderDetails().getUserInfoEndpoint().getUri();
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         headers.set("Authorization", "Bearer " + accessToken);
         HttpEntity<String> entity = new HttpEntity<>(headers);
         RestTemplate template = new RestTemplate();
         ResponseEntity<String> response = template.exchange(userInfoEndpoint, HttpMethod.POST, entity, String.class);

         String userInfo = response.getBody();
         logger.info("유저 정보 확인" + userInfo);
         ObjectMapper mapper = new ObjectMapper();
         try {
             return mapper.readTree(userInfo);
         } catch (JsonProcessingException e) {
             logger.error("JSON 파싱 에러", e);
             return null;
         }
	}

	@Override
	public String generateRandomState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
	}

	@Override
	public void securityLogin(CustomUserDetails user) {
		logger.info("시큐리티 로그인 수동으로 설정");
		 Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	@Override
	public String buildUrl(ClientRegistration registration, String state) {
		logger.info("buildUrl 호출");
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(registration.getProviderDetails().getAuthorizationUri())
		        .queryParam("response_type", "code")
		        .queryParam("client_id", registration.getClientId())
		        .queryParam("redirect_uri", registration.getRedirectUriTemplate())
		        .queryParam("state", state);
		
		if("google".equals(registration.getRegistrationId())) {
			 builder.queryParam("scope", registration.getScopes());
		}
		logger.info("호출 uri " + builder.toUriString());
		return  builder.toUriString();
	}

}
