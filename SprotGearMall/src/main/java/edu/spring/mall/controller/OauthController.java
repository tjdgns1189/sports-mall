package edu.spring.mall.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.security.CustomUserDetails;
import edu.spring.mall.service.MemberService;
import edu.spring.mall.service.OAuthService;

@Controller
@RequestMapping(value = "oauth")
public class OauthController {
    private final Logger logger = LoggerFactory.getLogger(OauthController.class);

    @Autowired
    private ClientRegistrationRepository social;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private OAuthService OAuth;

    @GetMapping("/naverOAuth")
    public String naveroauthGET(HttpServletRequest request){
        logger.info("naveroauthGET호출");
        String path = OAuth.createUrl(request, "naver");
        return "redirect:" + path;
    }
    

    
    

    @GetMapping("/naver")
    public String naverGET(HttpServletRequest request, Model model,
                         @RequestParam(required = false) String code,
                         @RequestParam(required = false) String state,
                         @RequestParam(required = false) String error,
                         @RequestParam(required = false) String error_description ) throws Exception {
        logger.info("naverGET리디렉션");

        if(error_description != null){
            logger.info("로그인 실패");
            return "redirect:/member/loginForm?error=error";
        }

        if (error != null) {
            // 에러 처리 로직
            logger.info("error발생" + error);
            model.addAttribute("error", "error");
            return "redirect:/member/loginForm?error=error";
        }
        String storedState = (String) request.getSession().getAttribute("naverOAuthState");
        if(storedState == null || !storedState.equals(state)) {
            // state 값 불일치 - 요청 위조 가능성
            throw new IllegalStateException("state값 불일치");
        }else{
        	//여기에 인증토큰 들어있음
            String tokenResponse = OAuth.getToken("naver", code, state);
            logger.info("tokenInfo" + tokenResponse);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(tokenResponse);
            String accessToken = rootNode.path("access_token").asText(); 
            
            JsonNode userNode = OAuth.getUserInfo("naver",accessToken);
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

                   return "redirect:/";

                }catch (UsernameNotFoundException e){
                	logger.info("회원 계정 없어서 로그인 진행 해야함");
                	String password = OAuth.generateRandomState();  
                	String memberGrade = "ROLE_USER";
                	MemberVO vo = new MemberVO(memberId, password, name, phone, null, null, memberGrade);
                	int result = memberService.create(vo);
                	
                	if(result == 1) {
                		logger.info("회원가입 성공");
                    	CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(memberId);
                    	OAuth.securityLogin(userDetails);
                	}else {
                		logger.info("회원가입 실패");
                        return "redirect:/member/loginForm?error=error";
                	}
              
                    return "redirect:/";

                }//end catch
            }//end if(resultcode.equals("00") && message.equals("success"))
            return "redirect:/";
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
        String path = OAuth.createUrl(request, "google");
    	return "redirect:" + path;
    }

}
