package edu.spring.mall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.api.client.http.HttpRequest;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.security.CustomUserDetails;
import edu.spring.mall.service.MemberService;
import edu.spring.mall.service.OAuthService;

@Controller
@RequestMapping(value = "oauth")
public class OauthController {
    private final Logger logger = LoggerFactory.getLogger(OauthController.class);
    private final String COL_GOOGLE = "google";
    private final String COL_NAVER = "naver";


    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private OAuthService OAuth;
    
 

    @GetMapping("/naverOAuth")
    public String naveroauthGET(HttpServletRequest request){
        logger.info("naveroauthGET호출");
        String path = OAuth.createUrl(request, COL_NAVER);
        return "redirect:" + path;
    }
    

    @GetMapping("/naver")
    public void naverGET(HttpServletRequest request, Model model,
                         @RequestParam(required = false) String code,
                         @RequestParam(required = false) String state,
                         @RequestParam(required = false) String error,
                         @RequestParam(required = false) String error_description,
                         HttpServletResponse response) throws Exception {
        logger.info("naverGET리디렉션");

        if(error_description != null){
            logger.info("로그인 실패");
    		response.sendRedirect("/member/loginForm?error=error");

        }

        if (error != null) {
            // 에러 처리 로직
            logger.info("error발생" + error);
    		response.sendRedirect("/member/loginForm?error=error");

        }
        String storedState = (String) request.getSession().getAttribute("naverOAuthState");
        if(storedState == null || !storedState.equals(state)) {
            // state 값 불일치 - 요청 위조 가능성
            throw new IllegalStateException("state값 불일치");
        }else{
        	//여기에 인증토큰 들어있음
        	OAuth2AccessToken tokenResponse = OAuth.getToken(COL_NAVER, code, state);
        	logger.info("tokenInfo: " + tokenResponse.getTokenValue());
        	String accessToken = tokenResponse.getTokenValue();
        	//여기에 유저정보 들어있음
        	JsonNode userNode = OAuth.getUserInfo("naver", tokenResponse);
            String resultcode = userNode.path("resultcode").asText();
            String message = userNode.path("message").asText();
            
            if(resultcode.equals("00") && message.equals("success")){
            	String memberId = userNode.path("response").path("id").asText();
            	String name = userNode.path("response").path("name").asText();
            	String phone = userNode.path("response").path("mobile").asText();
            	logger.info("아이디 : " + memberId);
            	logger.info("이름 : " + name);
            	logger.info("연락처 : " + phone );

                try {
                    //로그인 수행 아이디 있다고 가졍하고 이동시키면되는데
                	//아이디 없으면 catch로 넘어감
                	CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(memberId);
                	OAuth.securityLogin(userDetails);
                	OAuth.saveToken(request, response, COL_NAVER, tokenResponse);

                }catch (UsernameNotFoundException e){
                	logger.info("회원 계정 없어서 로그인 진행 해야함");
                	String password = OAuth.generateRandomState();  
                	String memberGrade = "ROLE_USER";
                	String isOauthLogin = "Y";
                	MemberVO vo = new MemberVO(memberId, password, name, phone, null, null, memberGrade, isOauthLogin);
                	int result = memberService.create(vo);
                	
                	if(result == 1) {
                		logger.info("회원가입 성공");
                    	CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(memberId);
                    	OAuth.securityLogin(userDetails);
                    	OAuth.saveToken(request, response, COL_NAVER, tokenResponse);
                	}else {
                		logger.info("회원가입 실패");
                		response.sendRedirect("/member/loginForm?error=error");
                	}
              
                	
                }//end catch
            }//end if(resultcode.equals("00") && message.equals("success"))
        }//end else(state 인증 성공시)
    }//end naverGET
    
    //밑쪽으론 아직 config안됨
    @GetMapping("/kakaoOAuth")
    public String kakaooauthGET(HttpServletRequest request) {
    	logger.info("카카오 로그인 api호출");
        String path = OAuth.createUrl(request, "kakao");
    	return "redirect:" + path;
    }
    
    @GetMapping("/googleOAuth")
    public String googleoauthGET(HttpServletRequest request) {
    	logger.info("구글 api호출");
        String path = OAuth.createUrl(request, COL_GOOGLE);
    	return "redirect:" + path;
    }
    
    
    @GetMapping("google")
    public void googleGET(HttpServletRequest request, Model model,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String error_description,
            HttpServletResponse response) 
            		throws Exception,IOException, ServletException{
    	logger.info("google 로그인 콜백");
    	
    	if(error != null) {
    		logger.info("인증 에러");
    		response.sendRedirect("/member/loginForm?error=error");
    	}
    	
    	if(error_description != null) {
    		logger.info("에러 발생");
    		response.sendRedirect("/member/loginForm?error=error");

    	}
    	
        String storedState = (String) request.getSession().getAttribute("googleOAuthState");
    	if(storedState == null || !storedState.equals(state)) {
            throw new IllegalStateException("state값 불일치");
    	}
    	
    	OAuth2AccessToken tokenResponse = OAuth.getToken(COL_GOOGLE, code, state);
        logger.info("tokenInfo" + tokenResponse.getTokenValue());
    	JsonNode userNode = OAuth.getUserInfo(COL_GOOGLE, tokenResponse);
        
        if(userNode == null) {
    		response.sendRedirect("/member/loginForm?error=error");
    	}
        String memberId = userNode.path("sub").asText();
        String name = userNode.path("name").asText();
        String email = userNode.path("email").asText();
    	logger.info("memberId" + memberId);
    	logger.info("name : " + name);
    	logger.info("email : " + email );
    	
    	try {
			CustomUserDetails user = 
					(CustomUserDetails) userDetailsService.loadUserByUsername(memberId);
			OAuth.securityLogin(user);
			logger.info("로그인 성공");
        	OAuth.saveToken(request, response, COL_GOOGLE, tokenResponse);
		} catch (UsernameNotFoundException e) {
			logger.info("회원가입 진행");
			String password = OAuth.generateRandomState();
			String userGrade= "ROLE_USER";
			String isOauthLogin = "Y";
			MemberVO vo = new MemberVO(memberId, password, name, null, email, null, userGrade, isOauthLogin);
			int result = memberService.create(vo);
			if(result == 1) {
				CustomUserDetails user = 
						(CustomUserDetails) userDetailsService.loadUserByUsername(memberId);
				OAuth.securityLogin(user);
				logger.info("로그인 성공");
            	OAuth.saveToken(request, response, COL_GOOGLE, tokenResponse);

			}
		}
    }//end googleGET

}
