package edu.spring.mall.controller;

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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.security.CustomUserDetails;
import edu.spring.mall.service.MemberService;

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

    @GetMapping("/naverOAuth")
    public String naveroauthGET(HttpServletRequest request){
        logger.info("naveroauthGET호출");
        ClientRegistration naverRegi= social.findByRegistrationId("naver");

        String clientId = naverRegi.getClientId();
        String redirectUri = naverRegi.getRedirectUriTemplate();
        String state = generateRandomState();
        request.getSession().setAttribute("naverOAuthState", state);


        String path ="https://nid.naver.com/oauth2.0/authorize?response_type=code" +
                "&client_id=" + clientId +
                "&state=" + state +
                "&redirect_uri=" + redirectUri;

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
            ClientRegistration naverRegi= social.findByRegistrationId("naver");
            RestTemplate restTemplate = new RestTemplate();
            String tokenEndPoint = "https://nid.naver.com/oauth2.0/token";

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", "H7PD5nLOq1YXa7eImpSC");
            params.add("client_secret", "8P2x1Hivt0");
            params.add("code", code);
            params.add("state", state);

            ResponseEntity<String> response = restTemplate.postForEntity(tokenEndPoint, params, String.class);
           //인증토큰 가져옴
            String tokenResponse = response.getBody(); // JSON 형태의 응답

            logger.info("tokenInfo" + tokenResponse);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(tokenResponse);
            String accessToken = rootNode.path("access_token").asText();


            String userInfoEndpoint = "https://openapi.naver.com/v1/nid/me";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + accessToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate template = new RestTemplate();

            ResponseEntity<String> rsp = template.exchange(userInfoEndpoint, HttpMethod.POST, entity, String.class);

            //사용자 정보 가져옴
            String userInfo = rsp.getBody();
            logger.info("userInfo" + userInfo);

            ObjectMapper userMapper = new ObjectMapper();
            JsonNode userNode = userMapper.readTree(userInfo);

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
                	securityLogin(userDetails);
                   return "redirect:/";

                }catch (UsernameNotFoundException e){
                	logger.info("회원 계정 없어서 로그인 진행 해야함");
                	String password = generateRandomState();
                	String memberGrade = "ROLE_USER";
                	MemberVO vo = new MemberVO(memberId, password, name, phone, null, null, memberGrade);
                	int result = memberService.create(vo);
                	
                	if(result == 1) {
                		logger.info("회원가입 성공");
                    	CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(memberId);
                    	securityLogin(userDetails);
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

    private String generateRandomState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
    
    public void securityLogin(CustomUserDetails user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
