package edu.spring.mall.service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.spring.mall.security.CustomUserDetails;
import edu.spring.mall.util.CookieUtil;
@Service
public class OAuthServiceImple implements OAuthService {
	private final Logger logger = LoggerFactory.getLogger(OAuthServiceImple.class);

	
	@Autowired
    private ClientRegistrationRepository social;
	
	@Autowired
	private OAuth2AuthorizedClientService authService;
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Override
	public String createUrl(HttpServletRequest request, String RegistrationId) {
		logger.info("url 생성 : " + RegistrationId);
        ClientRegistration registration= social.findByRegistrationId(RegistrationId);
        if(registration ==null) {
        	return  "false";
        }
        
        String state = generateRandomState();
        request.getSession().setAttribute(RegistrationId + "OAuthState", state);  
        logger.info("세션 등록 키값 확인 :" + RegistrationId + "OAuthState");
		return buildUrl(registration, state);
	}

	@Override
	public OAuth2AccessToken  getToken(String registrationId, String code, String state) throws JsonMappingException, JsonProcessingException {
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
		    
			if("google".equals(registration.getRegistrationId())) {
				params.add("redirect_uri", registration.getRedirectUri());
			}
			
		    ResponseEntity<String> response = restTemplate.postForEntity(tokenEndpoint, params, String.class);
		    String tokenJson = response.getBody();
		    logger.info("token값 : " + tokenJson);
		    ObjectMapper objectMapper = new ObjectMapper();
		    JsonNode jsonNode = objectMapper.readTree(tokenJson);
		    String accessTokenValue = jsonNode.get("access_token").asText();
		    long expiresIn = jsonNode.get("expires_in").asLong();

		    OAuth2AccessToken accessToken;
		    
		    if("google".equals(registrationId)) {
		        String scopeString = jsonNode.get("scope").asText();
		        Set<String> scopes = Arrays.stream(scopeString.split(" ")).collect(Collectors.toSet());
		        accessToken = new OAuth2AccessToken(
		            OAuth2AccessToken.TokenType.BEARER, accessTokenValue, Instant.now(),
		            Instant.now().plusSeconds(expiresIn), scopes
		        );
		    } else {
		        accessToken = new OAuth2AccessToken(
		            OAuth2AccessToken.TokenType.BEARER, accessTokenValue, Instant.now(),
		            Instant.now().plusSeconds(expiresIn)
		        );
		    }

		    return accessToken;
		
	}

	@Override
	public JsonNode getUserInfo(String registrationId, OAuth2AccessToken accessToken) {
		logger.info("getUserInfo 호출");
		String access = accessToken.getTokenValue();
		 ClientRegistration registration = social.findByRegistrationId(registrationId);
		 String userInfoEndpoint = registration.getProviderDetails().getUserInfoEndpoint().getUri();
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         headers.set("Authorization", "Bearer " + access);
         HttpEntity<String> entity = new HttpEntity<>(headers);
         RestTemplate template = new RestTemplate();
         ResponseEntity<String> response = template.exchange(userInfoEndpoint, HttpMethod.POST, entity, String.class);
         String userInfo = response.getBody();
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
			 String scopes = registration.getScopes().stream()
                     .collect(Collectors.joining(" "));
			 builder.queryParam("scope", scopes);
		}
		logger.info("호출 uri " + builder.toUriString());
		return  builder.toUriString();
	}

	@Override
    public void saveToken(HttpServletRequest request, HttpServletResponse response, 
    		String registaionId, OAuth2AccessToken accessToken) throws IOException, ServletException {
		logger.info("saveToken 호출");
		
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		logger.info("principal.getName() : " + principal.getName());
		  ClientRegistration registration = social.findByRegistrationId(registaionId);
		    OAuth2AuthorizedClient authorizedClient 
		    = new OAuth2AuthorizedClient(registration, principal.getName(), accessToken);
		    
		    authService.saveAuthorizedClient(authorizedClient, principal);
		    
	
		    logger.info("현재 로그인중인 계정" + principal.getName());
		    HttpSession session = request.getSession();
	        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            
	        Cookie cookie = new Cookie("registrationId", registaionId);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
            try {
				CookieUtil.createEncryptedCookie(response, "Token", accessToken.getTokenValue(), 60*60*24);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    successHandler.onAuthenticationSuccess(request, response, principal);
		    
	}



}
